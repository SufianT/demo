package com.example.demo.web;

import com.example.demo.model.Authenticator;
import com.example.demo.model.Database;
import com.example.demo.model.FineManager;
import com.example.demo.model.LoanSystem;
import com.example.demo.model.Person;
import com.example.demo.model.User;
import com.example.demo.model.exceptions.BookNotAvailableException;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {
    private final LoanSystem ls;
    private final Authenticator auth;
    private final FineManager fm;

    public LoanController(LoanSystem ls, Authenticator auth, FineManager fm) {
        this.ls = ls;
        this.auth = auth;
        this.fm = fm;
    }

    @GetMapping("/history")
    public List<User.LoanLog> onGetHistory(@RequestParam String token) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            return u.getLogs();
        }

        return List.of();
    }

    @GetMapping("/saved")
    public List<String> onGetSaved(@RequestParam String token) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            return u.getSaved();
        }

        return List.of();
    }

    @GetMapping("/favorite")
    public Map<String, Object> onMakeFavorite(@RequestParam String token, @RequestParam String isbn) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            var saved = u.getSaved();
            saved.add(isbn);
            Database.updateUser(u);

            return Map.of("success", true);
        }
        return Map.of("success", false, "message", "idk");
    }

    @PostMapping("/borrow")
    public Map<String, Object> onborrowBook(@RequestBody BodyOfBorrowOrReturnBook body) {
        Person p = auth.exchange(body.token);

        if (p instanceof User u) {
            try {
                ls.borrow(u, body.isbn());
                return Map.of("success", true);
            } catch (BookNotAvailableException e) {
                System.out.println(e);
                return Map.of("success", false, "message", "book not available");
            }
        }

        // figure out the error
        return Map.of("success", false, "message", "");
    }

    @PostMapping("/return")
    public Map<String, Object> onReturnBook(@RequestBody BodyOfBorrowOrReturnBook body) {
        Person p = auth.exchange(body.token);

        if (p instanceof User u) {
            ls.returnBook(u, body.isbn());
            return Map.of("success", true);
        }

        // figure out the error
        return Map.of("success", false, "message", "person not a user");
    }

    @GetMapping("/fines")
    public List<FineManager.FineInstance> onGetFines(@RequestParam String token) {
        Person p = auth.exchange(token);

        if (p instanceof User u) {
            return fm.HandleFine(u);
        }

        return List.of();

    }

    private record BodyOfBorrowOrReturnBook(String token, String isbn) {
    }
}

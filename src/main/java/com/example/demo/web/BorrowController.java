package com.example.demo.web;

import com.example.demo.model.Book;
import com.example.demo.model.LibrarySystem;
import com.example.demo.model.LoanSystem.BorrowedBook;
import com.example.demo.model.exceptions.PersonExistException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowController {
    private final LibrarySystem ls;

    public BorrowController(LibrarySystem ls) {
        this.ls = ls;
    }

    // @PostMapping("/borrow")
    // public Map<String, Object> onborrowBook(@RequestBody BodyOfBorrowBook requestBody) {
    //    try {
           
            
    //         return Map.of("success", true);
    //     } catch () { 
    //         return Map.of("success", false, "message", "person already exists");
    //     }
    // }


    // @PostMapping("/return")
    // public Map<String, Object> returnbook(@RequestBody Map<String, String> requestBody) {
    
    // } 

    public record BodyOfBorrowBook(String isbn, String startDate, String id) {
    }
}

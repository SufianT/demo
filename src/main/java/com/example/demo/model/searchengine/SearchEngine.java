package com.example.demo.model.searchengine;

import com.example.demo.model.Book;
import com.example.demo.model.Database;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class SearchEngine implements SearchInterface {
    // word -> [ISBNS]
    HashMap<String, List<String>> words = new HashMap<>();

    public SearchEngine() {
        for (Book book : Database.getBookList()) {
            // ISBN
            distribute(book.getISBN(), book);

            // whole title
            distribute(book.getTitle(), book);

            // parts of the title
            for (String part : book.getTitle().split("\\s+"))
                distribute(part, book);

            // authors
            for (String author : book.getAuthors()) {
                // whole author name
                distribute(author, book);

                // part of author names
                for (String part : author.split("\\s+"))
                    distribute(part, book);
            }
        }
    }

    private void distribute(String input, Book book) {
        input = input.toUpperCase();
        List<String> items = words.get(input);
        if (items == null) {
            items = new ArrayList<>();
            items.add(book.getISBN());
            words.put(input, items);
        } else
            items.add(book.getISBN());
    }

    @Override
    public Set<String> find(String input) {
        List<String> isbns = words.get(input.strip().toUpperCase());
        if (isbns == null)
            return Collections.emptySet();
        return new LinkedHashSet<String>(isbns);
    }
}

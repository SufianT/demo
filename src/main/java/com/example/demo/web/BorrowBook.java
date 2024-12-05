package com.example.demo.web;

import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BorrowBook {
    
    @PostMapping("/index")
    public String onPostBorrow() {
        //TODO: process POST request
        
        return null;
    }
    
}

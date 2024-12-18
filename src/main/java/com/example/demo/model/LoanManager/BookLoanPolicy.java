package com.example.demo.model;

public class BookLoanPolicy implements LoanPolicy { //For our book loan duration which is 2 weeks

    @Override
    public int getLoanDurationDays() {
        return 14;
    }
    
}

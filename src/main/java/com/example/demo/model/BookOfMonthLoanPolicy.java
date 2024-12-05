package com.example.demo.model;

public class BookOfMonthLoanPolicy implements LoanPolicy{ //Loan duration for book of the month
    @Override
    public int getLoanDurationDays() {
       
        return 3;
    }
}

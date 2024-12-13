package com.example.demo.model;

import java.time.LocalDate;

/**
 * This is the loanperiod for a user and a book
 */
public class LoanPeriod {
    private LocalDate startDate; //startdate of book loan
    private LocalDate endDate; //endDate of book loan

    public LoanPeriod(LocalDate startDate, LoanPolicy loanPolicy) {
        this.startDate = startDate;
        this.endDate = DateCalculator.calculateDueDate(startDate, loanPolicy.getLoanDurationDays()); //Calculates endDate based on loanPolicy

    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String toString(LocalDate localDate){
        return localDate.toString();
    }

    public void extendLoan(int extraDays) { //If User wishes to extend loan and there is no reservation Admin should be able to do so
        endDate = endDate.plusDays(extraDays);
    }

    public boolean isOverDue(LocalDate currentDate) { //Checks if the book is overdue
        return currentDate.isAfter(endDate);
    }

    public int getOverDueDays(LocalDate currentDate) { //return how many overdue days
        if (!isOverDue(currentDate))
            return 0;
        return DateCalculator.calculateDaysBetween(endDate, currentDate);
    }

}

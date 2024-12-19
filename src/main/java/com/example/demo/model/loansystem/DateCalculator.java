package com.example.demo.model.loansystem;

import java.time.LocalDate;

public class DateCalculator { //Calculates due date and days between dates

    public static LocalDate calculateDueDate(LocalDate startDate, int loanDurationDays){ 
        return startDate.plusDays(loanDurationDays);
    }

    public static int calculateDaysBetween(LocalDate date1, LocalDate date2){
        return (int) java.time.temporal.ChronoUnit.DAYS.between(date1, date2);
    }
    
}
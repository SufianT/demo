package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FineManager {


    public FineManager() {

    }

    public List<FineInstance> HandleFine(User user){
        List<FineInstance> fines = new ArrayList<FineInstance>();
        LoanPolicy policy = new BookLoanPolicy();
        for (int i = 0; i < user.getLoans().size(); i++) {
            for (int j = user.getLogs().size() - 1; j >= 0; j--) {
                if (user.getLoans().get(i).equals(user.getLogs().get(j).isbn())) {
                    LoanPeriod period = new LoanPeriod(LoanPeriod.parseDate(user.getLogs().get(j).time()), policy);
                    int fine = FineCalculator.calculateFine(period.getOverDueDays(LocalDate.now()));
                    fines.add(new FineInstance(fine, user.getLoans().get(i)));
                    break;
                }

            }

        }
        return fines;
    }

    // pay fines before returning book!!
    public record FineInstance(int fine, String isbn) {

    }
}

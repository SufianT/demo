package com.example.demo.model.finesystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.loansystem.BookLoanPolicy;
import com.example.demo.model.loansystem.LoanPeriod;
import com.example.demo.model.loansystem.LoanPolicy;
import com.example.demo.model.usermanagement.User;

/**
 * Manages fines for overdue loans in the library system.
 * - Calculates overdue fines for a user's active loans based on loan policies
 * and logs.
 * - Generates a list of fine instances containing the fine amount and the
 * associated ISBN.
 * 
 * Key Methods:
 * - `HandleFine`: Processes a user's loans and logs to calculate fines for
 * overdue items.
 * 
 * Includes:
 * - `FineInstance`: A record representing a fine with the fine amount and
 * associated ISBN.
 */

@Service
public class FineManager {

    public FineManager() {

    }

    public List<FineInstance> HandleFine(User user) {
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

    public record FineInstance(int fine, String isbn) {
    }

}

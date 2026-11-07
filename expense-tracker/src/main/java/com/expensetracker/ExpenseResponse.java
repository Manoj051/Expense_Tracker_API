
package com.expensetracker;

import java.time.LocalDate;

public class ExpenseResponse {

    private Long id;
    private String title;
    private double amount;
    private String category;
    private LocalDate expenseDate;

    public ExpenseResponse(
            Long id,
            String title,
            double amount,
            String category,
            LocalDate expenseDate) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.expenseDate = expenseDate;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }
}

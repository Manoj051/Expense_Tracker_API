package com.expensetracker;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategoryIgnoreCase(String category);

    List<Expense> findByExpenseDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );

}


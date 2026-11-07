package com.expensetracker;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService ;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(
            @Valid @RequestBody ExpenseRequest request) {

        Expense expense = new Expense(
                request.getTitle(),
                request.getAmount(),
                request.getCategory(),
                request.getExpenseDate()
        );

        Expense savedExpense = expenseService.createExpense(expense);

        ExpenseResponse response = new ExpenseResponse(
                savedExpense.getId(),
                savedExpense.getTitle(),
                savedExpense.getAmount(),
                savedExpense.getCategory(),
                savedExpense.getExpenseDate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {

        List<Expense> expenses = expenseService.getAllExpenses();

        List<ExpenseResponse> responses = expenses.stream()
                .map(expense -> new ExpenseResponse(
                        expense.getId(),
                        expense.getTitle(),
                        expense.getAmount(),
                        expense.getCategory(),
                        expense.getExpenseDate()
                ))
                .toList();

        return ResponseEntity.ok(responses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable Long id) {

        Expense expense = expenseService.getExpenseById(id);

        ExpenseResponse response = new ExpenseResponse(
                expense.getId(),
                expense.getTitle(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getExpenseDate()
        );

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense updatedExpense) {

        Expense expense = expenseService.updateExpense(id, updatedExpense);
        return ResponseEntity.ok(expense);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(
            @PathVariable String category) {

        List<Expense> expenses =
                expenseService.getExpensesByCategory(category);

        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Expense>> getExpensesByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<Expense> expenses =
                expenseService.getExpensesByDateRange(startDate, endDate);

        return ResponseEntity.ok(expenses);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<Expense>> getExpensesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<Expense> expenses =
                expenseService.getExpensesWithPagination(page, size);

        return ResponseEntity.ok(expenses);
    }


}

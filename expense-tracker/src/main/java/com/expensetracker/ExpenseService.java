package com.expensetracker;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository = expenseRepository;
    }
    public Expense createExpense(Expense expense){
        return expenseRepository.save(expense);
    }
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }


    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }


    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = getExpenseById(id);

        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setCategory(updatedExpense.getCategory());
        existingExpense.setExpenseDate(updatedExpense.getExpenseDate());

        return expenseRepository.save(existingExpense);
    }

    public void deleteExpense(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
    }

    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category);
    }


    public List<Expense> getExpensesByDateRange(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start date must be before or equal to end date");
        }

        return expenseRepository.findByExpenseDateBetween(
                startDate, endDate);
    }

    public Page<Expense> getExpensesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("expenseDate").descending()
        );

        return expenseRepository.findAll(pageable);
    }

}

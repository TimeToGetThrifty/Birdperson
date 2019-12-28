package com.greenback.services;

import com.greenback.entities.Expense
import com.greenback.repositories.ExpenseRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class ExpenseService(private val repository: ExpenseRepository) {
    fun save(expense: Expense, principal: Principal): Expense {
        expense.createUserId = principal.name
        return repository.save(expense)
    }

    fun get(id: String, principal: Principal): Expense {
        return repository.findByIdAndCreateUserId(id, principal.name)
    }

    fun get(principal: Principal): List<Expense> {
        return repository.findByCreateUserId(principal.name)
    }
}
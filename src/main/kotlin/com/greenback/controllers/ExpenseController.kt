package com.greenback.controllers

import com.greenback.entities.Expense
import com.greenback.services.ExpenseService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController("/greenback/api")
class ExpenseController(private val service: ExpenseService) {
    @PostMapping("/expenses")
    fun save(@RequestBody expense: Expense, principal: Principal): Expense {
        return service.save(expense, principal)
    }

    @GetMapping("/expenses/{id}")
    fun get(@PathVariable id: String, principal: Principal): Expense {
        return service.get(id, principal)
    }

    @GetMapping("/expense")
    fun get(principal: Principal): List<Expense> {
        return service.get(principal)
    }
}
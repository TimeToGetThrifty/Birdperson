package com.greenback.controllers

import com.greenback.entities.Transaction
import com.greenback.services.TransactionService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController("/greenback/api")
class TransactionController(private val service: TransactionService) {
    @PostMapping("/transactions")
    fun save(@RequestBody transaction: Transaction, principal: Principal): Transaction {
        return service.save(transaction, principal)
    }

    @GetMapping("/transactions/{id}")
    fun get(@PathVariable id: String, principal: Principal): Transaction {
        return service.get(id, principal)
    }

    @GetMapping("/transactions")
    fun get(principal: Principal): List<Transaction> {
        return service.get(principal)
    }
}
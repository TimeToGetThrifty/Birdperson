package com.greenback.services

import com.greenback.entities.Transaction
import com.greenback.repositories.TransactionRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.security.Principal

@Service
class TransactionService(private val repository: TransactionRepository) {
    @PostMapping("/transactions")
    fun save(@RequestBody transaction: Transaction, principal: Principal): Transaction {
        transaction.createUserId = principal.name
        return repository.save(transaction)
    }

    @GetMapping("/transactions/{id}")
    fun get(@PathVariable id: String, principal: Principal): Transaction {
        return repository.findByIdAndCreateUserId(id, principal.name)
    }

    @GetMapping("/transactions")
    fun get(principal: Principal): List<Transaction> {
        return repository.findByCreateUserId(principal.name)
    }
}
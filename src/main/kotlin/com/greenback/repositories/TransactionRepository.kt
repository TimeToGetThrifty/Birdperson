package com.greenback.repositories

import com.greenback.entities.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: CrudRepository<Transaction, String> {
    fun findByIdAndCreateUserId(id: String, createUserId: String): Transaction
    fun findByCreateUserId(createUserId: String): List<Transaction>
}
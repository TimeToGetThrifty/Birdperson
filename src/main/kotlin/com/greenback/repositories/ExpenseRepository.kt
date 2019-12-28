package com.greenback.repositories

import com.greenback.entities.Expense
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : CrudRepository<Expense, String> {
    fun findByIdAndCreateUserId(id: String, createUserId: String): Expense
    fun findByCreateUserId(createUserId: String): List<Expense>
}
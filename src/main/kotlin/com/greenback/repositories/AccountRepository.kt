package com.greenback.repositories

import com.greenback.entities.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository: CrudRepository<Account, String> {
    fun findByIdAndCreateUserId(id: String, createUserId: String): Account
    fun findByCreateUserId(createUserId: String): List<Account>
}
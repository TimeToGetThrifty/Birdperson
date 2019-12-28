package com.greenback.services

import com.greenback.entities.Account
import com.greenback.repositories.AccountRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class AccountService(private val repository: AccountRepository) {
    fun save(account: Account, principal: Principal): Account {
        account.createUserId = principal.name
        return repository.save(account)
    }

    fun get(id: String, principal: Principal): Account {
        return repository.findByIdAndCreateUserId(id, principal.name)
    }

    fun get(principal: Principal): List<Account> {
        return repository.findByCreateUserId(principal.name)
    }
}
package com.greenback.controllers;

import com.greenback.entities.Account
import com.greenback.services.AccountService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/greenback/api")
class AccountController(private val service: AccountService) {
    @PostMapping("/accounts")
    fun save(@RequestBody account: Account, principal: Principal): Account {
        return service.save(account, principal)
    }

    @GetMapping("/accounts/{id}")
    fun get(@PathVariable id: String, principal: Principal): Account {
        return service.get(id, principal)
    }

    @GetMapping("/accounts")
    fun get(principal: Principal): List<Account> {
        return service.get(principal)
    }
}

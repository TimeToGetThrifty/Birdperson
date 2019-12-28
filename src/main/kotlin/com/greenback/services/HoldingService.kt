package com.greenback.services

import com.greenback.entities.Holding
import com.greenback.repositories.HoldingRepository
import org.springframework.stereotype.Service
import java.security.Principal

@Service
class HoldingService(private val repository: HoldingRepository) {
    fun save(holding: Holding, principal: Principal): Holding {
        holding.createUserId = principal.name
        return repository.save(holding)
    }

    fun get(id: String, principal: Principal): Holding {
        return repository.findByIdAndCreateUserId(id, principal.name)
    }

    fun get(principal: Principal): List<Holding> {
        return repository.findByCreateUserId(principal.name)
    }
}
package com.greenback.repositories

import com.greenback.entities.Holding
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface HoldingRepository : CrudRepository<Holding, String> {
    fun findByIdAndCreateUserId(id: String, createUserId: String): Holding
    fun findByCreateUserId(createUserId: String): List<Holding>
}
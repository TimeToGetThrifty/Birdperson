package com.greenback.controllers

import com.greenback.entities.Holding
import com.greenback.services.HoldingService
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/greenback/api")
class HoldingController(private val service: HoldingService) {
    @PostMapping("/holdings")
    fun save(@RequestBody holding: Holding, principal: Principal): Holding {
        return service.save(holding, principal)
    }

    @GetMapping("/holdings/{id}")
    fun get(@PathVariable id: String, principal: Principal): Holding {
        return service.get(id, principal)
    }

    @GetMapping("/holdings")
    fun get(principal: Principal): List<Holding> {
        return service.get(principal)
    }
}
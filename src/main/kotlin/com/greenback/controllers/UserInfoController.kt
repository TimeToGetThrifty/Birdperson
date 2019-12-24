package com.greenback.controllers

import com.greenback.entities.UserInfo
import com.greenback.services.UserInfoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfoController(private val service: UserInfoService) {
    @PostMapping("/userInfo")
    fun createUser(@RequestBody userInfo: UserInfo): UserInfo {
        return service.createUser(userInfo)
    }
}
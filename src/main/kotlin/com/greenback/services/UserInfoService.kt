package com.greenback.services

import com.greenback.entities.UserInfo
import com.greenback.repositories.UserInfoRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserInfoService(private val repository: UserInfoRepository, private val passwordEncoder: PasswordEncoder) {
    fun createUser(userInfo: UserInfo): UserInfo {
        return repository.save(
            UserInfo(
                userName = userInfo.userName,
                password = passwordEncoder.encode(userInfo.password)
            )
        )
    }
}
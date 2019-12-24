package com.greenback.repositories

import com.greenback.entities.UserInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserInfoRepository : JpaRepository<UserInfo, UUID> {
    fun findByUserName(userName: String): UserInfo
}
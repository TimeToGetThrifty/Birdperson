package com.greenback.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserInfo(
    @Id val id: UUID = UUID.randomUUID(),
    @Column(unique = true) val userName: String,
    val password: String,
    val active: Boolean = true,
    val role: String = "ROLE_USER"
)
package com.greenback.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Account (
    @Id val id: String = UUID.randomUUID().toString(),
    val type: AccountType,
    val name: String,
    var createUserId: String? = null
)

enum class AccountType {
    BANK,
    BROKERAGE,
    EXPENSE
}

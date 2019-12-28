package com.greenback.entities

import java.util.*
import javax.persistence.Entity

@Entity
class Account (
    val id: String = UUID.randomUUID().toString(),
    val type: AccountType,
    val name: String,
    var createUserId: String? = null
)

enum class AccountType {
    BANK,
    BROKERAGE,
    EXPENSE
}

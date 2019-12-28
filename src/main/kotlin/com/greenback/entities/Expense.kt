package com.greenback.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity

@Entity
class Expense(
    val id: String = UUID.randomUUID().toString(),
    val accountId: String,
    val amount: BigDecimal,
    val category: String? = null,
    val date: Date = Date(),
    val description: String? = null,
    var createUserId: String?
)
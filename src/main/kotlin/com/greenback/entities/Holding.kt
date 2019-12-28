package com.greenback.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity

@Entity
class Holding(
    val id: String = UUID.randomUUID().toString(),
    val accountId: String,
    val ticker: String,
    val quantity: BigDecimal,
    var createUserId: String
)
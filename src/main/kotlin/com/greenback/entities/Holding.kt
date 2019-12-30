package com.greenback.entities

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Holding(
    @Id val id: String = UUID.randomUUID().toString(),
    val accountId: String,
    val ticker: String,
    val quantity: BigDecimal,
    var createUserId: String
)
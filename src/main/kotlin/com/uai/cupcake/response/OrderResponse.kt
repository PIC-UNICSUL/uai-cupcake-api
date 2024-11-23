package com.uai.cupcake.response

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

data class OrderResponse(
    val id: UUID,
    val user: UserResponse,
    val status: String,
    val total: Double,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val orderItems: List<OrderItemResponse>
)

data class OrderItemResponse(
    val productId: UUID,
    val quantity: Int,
    val price: BigDecimal
)
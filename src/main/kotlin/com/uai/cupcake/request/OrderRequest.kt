package com.uai.cupcake.request

import java.util.*


data class OrderRequest(
    val orderItems: List<OrderItemRequest>
)

data class OrderItemRequest(
    val id: UUID,
    val quantity: Int
)
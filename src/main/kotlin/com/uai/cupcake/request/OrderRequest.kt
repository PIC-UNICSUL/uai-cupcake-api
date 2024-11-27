package com.uai.cupcake.request

import jakarta.validation.constraints.NotBlank
import java.util.*


data class OrderRequest(
    val orderItems: List<OrderItemRequest>
)

data class OrderItemRequest(
    @field:NotBlank(message = "ID must not be blank")
    val id: UUID,
    val quantity: Int
)
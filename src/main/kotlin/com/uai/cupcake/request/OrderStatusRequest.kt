package com.uai.cupcake.request

import java.util.*

data class OrderStatusRequest(
    val id: UUID,
    val status: String,
    val additionalInfo: String
)

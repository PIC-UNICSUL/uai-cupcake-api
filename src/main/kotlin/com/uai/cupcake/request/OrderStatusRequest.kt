package com.uai.cupcake.request

import jakarta.validation.constraints.NotBlank
import java.util.*

data class OrderStatusRequest(
    val id: UUID,
    @field:NotBlank(message = "Status must not be blank")
    val status: String,
    val additionalInfo: String
)

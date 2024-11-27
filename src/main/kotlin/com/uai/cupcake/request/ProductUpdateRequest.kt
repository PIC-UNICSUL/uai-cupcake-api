package com.uai.cupcake.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal
import java.util.*

data class ProductUpdateRequest(
    @field:NotBlank(message = "id must not be blank")
    val id: UUID,
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    val description: String?,
    @field:NotBlank(message = "Category must not be blank")
    val category: String,
    @field:Min(value = 1, message = "The price cannot be less than 1")
    val price: BigDecimal,
    val photos: String?
)

package com.uai.cupcake.response

import java.math.BigDecimal
import java.util.UUID

data class ProductResponse(
    val id: UUID,
    val name: String,
    val description: String?,
    val category: String?,
    val price: BigDecimal,
    val photos: String?,
)
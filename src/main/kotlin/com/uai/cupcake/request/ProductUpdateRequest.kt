package com.uai.cupcake.request

import java.math.BigDecimal
import java.util.*

data class ProductUpdateRequest(
    val id: UUID,
    val name: String,
    val description: String?,
    val category: String,
    val price: BigDecimal,
    val photos: String?
)

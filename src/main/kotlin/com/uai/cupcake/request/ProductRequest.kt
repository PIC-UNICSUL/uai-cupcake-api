package com.uai.cupcake.request

import java.math.BigDecimal

data class ProductRequest(
    val name: String,
    val description: String?,
    val category: String,
    val price: BigDecimal,
    val photos: String?
)

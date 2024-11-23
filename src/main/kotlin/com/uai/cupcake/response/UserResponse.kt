package com.uai.cupcake.response

import java.util.*

data class UserResponse(
    val id: UUID,
    val name: String,
    val mail: String,
    val phone: String,
    val role: String
)

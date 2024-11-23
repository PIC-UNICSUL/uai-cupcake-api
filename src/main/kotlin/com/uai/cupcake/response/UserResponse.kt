package com.uai.cupcake.response

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val name: String,
    val mail: String,
    val phone: String
)

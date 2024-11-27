package com.uai.cupcake.request

import jakarta.validation.constraints.NotBlank

data class UserUpdateRequest(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    val phone: String
)

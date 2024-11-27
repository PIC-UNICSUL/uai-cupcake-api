package com.uai.cupcake.request

import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank(message = "Name must not be blank")
    val name: String,
    @field:NotBlank(message = "Password must not be blank")
    val password: String,
    @field:NotBlank(message = "Mail must not be blank")
    val mail: String,
    val phone: String
)

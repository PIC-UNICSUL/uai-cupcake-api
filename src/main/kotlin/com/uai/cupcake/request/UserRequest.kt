package com.uai.cupcake.request

data class UserRequest(
    val name: String,
    val password: String,
    val mail: String,
    val phone: String
)

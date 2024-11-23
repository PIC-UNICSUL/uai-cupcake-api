package com.uai.cupcake.exception

open class BusinessException(
    val errorCode: String,
    message: String
) : RuntimeException(message)
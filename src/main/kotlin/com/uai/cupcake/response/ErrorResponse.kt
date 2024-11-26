package com.uai.cupcake.response

import com.uai.cupcake.exception.ErrorCodeEnum

data class ErrorResponse(
    val code: ErrorCodeEnum,
    val message: String,
    val msgArg: String?
)

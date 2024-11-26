package com.uai.cupcake.exception

import com.uai.cupcake.response.ErrorResponse
import org.springframework.http.HttpStatus

open class CustomException(
    val httpStatus: HttpStatus,
    open val errorResponse: ErrorResponse
) : RuntimeException()
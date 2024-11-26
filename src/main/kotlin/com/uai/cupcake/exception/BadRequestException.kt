package com.uai.cupcake.exception

import com.uai.cupcake.response.ErrorResponse
import org.springframework.http.HttpStatus

open class BadRequestException(
    override val errorResponse: ErrorResponse
) : CustomException(HttpStatus.BAD_REQUEST, errorResponse)
package com.uai.cupcake.exception

import com.uai.cupcake.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        val errorResponse = when (ex.errorCode) {
            "BAD_REQUEST" -> ErrorResponse(message = ex.message ?: "Bad request", code = "BAD_REQUEST")
            "NOT_FOUND" -> ErrorResponse(message = ex.message ?: "Resource not found", code = "NOT_FOUND")
            else -> ErrorResponse(message = ex.message ?: "An unexpected error occurred", code = "I'M_A_TEAPOT")
        }

        return when (ex.errorCode) {
            "BAD_REQUEST" -> ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
            "NOT_FOUND" -> ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
            else -> ResponseEntity(errorResponse, HttpStatus.I_AM_A_TEAPOT)
        }
    }
}
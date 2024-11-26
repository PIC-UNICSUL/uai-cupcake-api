package com.uai.cupcake.exception

import com.uai.cupcake.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.sql.SQLException


@RestControllerAdvice
class GlobalExceptionHandler{

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(SQLException::class, DataAccessException::class)
    fun handleUnexpectedException(ex: Exception?): ResponseEntity<List<ErrorResponse>> {
        logger.error("Unexpected exception: ${ex?.message}", ex)

        val errorResponse = listOf(
            ErrorResponse(
                code = ErrorCodeEnum.I_AM_A_TEA_POT,
                message = ex?.message ?: "Bad Request",
                msgArg = ex?.stackTrace.toString()
            )
        )

        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errorResponse)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBusinessException(ex: BadRequestException): ResponseEntity<List<ErrorResponse>> {
        val errorResponse = listOf(
            ErrorResponse(
                code = ex.errorResponse.code,
                message = ex.errorResponse.message,
                msgArg = null
            )
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ResponseEntity<List<ErrorResponse>> {
        val errors = ex.fieldErrors.map { fieldError ->
            ErrorResponse(
                code = ErrorCodeEnum.INVALID_FIELD,
                message = fieldError.defaultMessage ?: "Invalid field",
                msgArg = fieldError.field
            )
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors)
    }

}

package com.hiandd.wiki.errors

import com.hiandd.wiki.dto.ExceptionResponse
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.utils.Log
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    companion object: Log{}

    val exceptionResponse = ExceptionResponse()

    @ExceptionHandler(NoIdException::class)
    fun noIdException(): ResponseEntity<ExceptionResponse?> {
        logger().error("noLinkDataException throw Exception : {}", ErrorCode.ID_NOT_FOUND.detail)
        return exceptionResponse.toResponseEntity(ErrorCode.ID_NOT_FOUND)
    }
}
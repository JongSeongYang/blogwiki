package com.hiandd.wiki.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.utils.Log
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime


class ExceptionResponse(
        @JsonSerialize(using = ZonedDateTimeSerializer::class)
        val timestamp:ZonedDateTime = ZonedDateTime.now(ZoneOffset.UTC),
        var status: Int = 0,
        var error: String? = null,
        var code: String? = null,
        var message: String? = null,
){
    companion object : Log{}

    fun toResponseEntity(errorCode: ErrorCode): ResponseEntity<ExceptionResponse?> {
        val exceptionResponse : ExceptionResponse = ExceptionResponse(
                status = errorCode.httpStatus.value(),
                error = errorCode.httpStatus.name,
                code = errorCode.name,
                message = errorCode.detail,
        )
        return ResponseEntity
                .status(errorCode.httpStatus)
                .body(exceptionResponse)
    }
}
package com.hiandd.wiki.enum

import org.springframework.http.HttpStatus

enum class ErrorCode (
        var httpStatus: HttpStatus,
        var detail: String,
        ){

    ID_NOT_FOUND(HttpStatus.NOT_FOUND,"There is no ID")

}
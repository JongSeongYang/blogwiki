package com.hiandd.wiki.dto

class CommonResponse {

    data class BooleanResponse(
            var result: Boolean = false,
            var message: String? = null
    )
}
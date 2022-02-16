package com.hiandd.wiki.errors

import com.hiandd.wiki.enum.ErrorCode

class NoIdException(private val errorCode: ErrorCode) : RuntimeException() {
}
package com.hiandd.wiki.controller

import com.hiandd.wiki.domain.Users
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.errors.NoIdException
import com.hiandd.wiki.service.UserService
import com.hiandd.wiki.utils.Log
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
public class UserController (
        private val userService: UserService
        ) {

    companion object : Log {}

    @ApiOperation(value = "user email 조회", notes = "user email 조회")
    @GetMapping(value = ["/{email}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<Users> {
        val findByEmail: Users = userService.findUserByEmail(email)
                ?: throw NoIdException(ErrorCode.ID_NOT_FOUND)
        return ResponseEntity.ok(findByEmail)
    }

    @ApiOperation(value = "all", notes = "all")
    @GetMapping(value = ["/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun all(): ResponseEntity<MutableList<Users>>? {
        val findAll = userService.findAllUserList()
        return ResponseEntity.ok(findAll)
    }
}
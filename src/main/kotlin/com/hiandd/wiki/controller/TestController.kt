package com.hiandd.wiki.controller

import com.hiandd.wiki.domain.Users
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.errors.NoIdException
import com.hiandd.wiki.repository.UserRepository
import com.hiandd.wiki.utils.Log
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/test")
public class TestController (
        private val userRepository: UserRepository
        ) {

    companion object : Log {}

    @ApiOperation(value = "user 조회 test", notes = "user 조회 test")
    @GetMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByEmail(@RequestParam email: String): ResponseEntity<Users> {

        val findByEmail1: Users? = userRepository.findByEmail(email)
        if(null == findByEmail1)
            logger().info("null")
        else
            logger().info(findByEmail1.email)

        val findByEmail: Users = userRepository.findByEmail(email)
                ?: throw NoIdException(ErrorCode.ID_NOT_FOUND)
        logger().info(findByEmail.name)
        return ResponseEntity.ok(findByEmail)
    }

    @ApiOperation(value = "test", notes = "test")
    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun test(@RequestParam email: String): ResponseEntity<String>? {
        return ResponseEntity.ok(email)
    }
}
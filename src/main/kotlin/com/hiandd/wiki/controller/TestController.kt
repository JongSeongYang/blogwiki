package com.hiandd.wiki.controller

import com.hiandd.wiki.domain.Posts
import com.hiandd.wiki.domain.Users
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.errors.NoIdException
import com.hiandd.wiki.repository.post.PostRepository
import com.hiandd.wiki.repository.user.UserRepository
import com.hiandd.wiki.utils.Log
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/test")
public class TestController (
        private val userRepository: UserRepository,
        private val postRepository: PostRepository
        ) {

    companion object : Log {}

    @ApiOperation(value = "user email 로 조회 test", notes = "user email 로 조회 test")
    @GetMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByEmail(@RequestParam email: String): ResponseEntity<Users> {
        val findByEmail: Users = userRepository.findByEmail(email)
                ?: throw NoIdException(ErrorCode.ID_NOT_FOUND)
        return ResponseEntity.ok(findByEmail)
    }

    @ApiOperation(value = "post id 로 조회 test", notes = "post id 로 조회 test")
    @GetMapping(value = ["/post"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPostById(@RequestParam id: String): ResponseEntity<Posts> {
        val findById = postRepository.findById(id).orElse(null)
        return ResponseEntity.ok(findById)
    }

    @ApiOperation(value = "test", notes = "test")
    @GetMapping(value = ["/one"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun test(@RequestParam email: String): ResponseEntity<String>? {
        return ResponseEntity.ok(email)
    }

    @ApiOperation(value = "all", notes = "all")
    @GetMapping(value = ["/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun all(): ResponseEntity<MutableList<Users>>? {
        val findAll = userRepository.findAll()
        logger().info("size: "+ findAll.size)
        return ResponseEntity.ok(findAll)
    }
}
package com.hiandd.wiki.controller

import com.hiandd.wiki.domain.Posts
import com.hiandd.wiki.domain.Users
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.errors.NoIdException
import com.hiandd.wiki.service.PostService
import com.hiandd.wiki.service.UserService
import com.hiandd.wiki.utils.Log
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/post")
public class PostController (
        private val postService: PostService
        ) {

    companion object : Log {}

    @ApiOperation(value = "post title 조회", notes = "post title 조회")
    @GetMapping(value = ["/{title}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByEmail(@PathVariable title: String): ResponseEntity<MutableList<Posts>>? {
        val findByTitle: MutableList<Posts> = postService.findAllByTitle(title)
        return ResponseEntity.ok(findByTitle)
    }

    @ApiOperation(value = "all post 조회", notes = "all post 조회")
    @GetMapping(value = ["/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun all(): ResponseEntity<MutableList<Posts>>? {
        val findAll = postService.findAllPost()
        return ResponseEntity.ok(findAll)
    }
}
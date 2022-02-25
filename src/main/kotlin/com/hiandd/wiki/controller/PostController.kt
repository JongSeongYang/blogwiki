package com.hiandd.wiki.controller

import com.hiandd.wiki.domain.Posts
import com.hiandd.wiki.dto.CommonResponse
import com.hiandd.wiki.dto.Post
import com.hiandd.wiki.service.PostService
import com.hiandd.wiki.utils.Log
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/v1/post")
public class PostController (
        private val postService: PostService
        ) {

    companion object : Log {}

    @ApiOperation(value = "post title 조회", notes = "post title 조회")
    @GetMapping(value = ["/{title}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByTitle(@PathVariable title: String): ResponseEntity<MutableList<Posts>>? {
        val findByTitle: MutableList<Posts> = postService.findAllByTitle(title)
        return ResponseEntity.ok(findByTitle)
    }

    @ApiOperation(value = "post title 조회", notes = "post title 조회 Querydsl")
    @GetMapping(value = ["/query/{title}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getUserByTitleWithQuerydsl(@PathVariable title: String): ResponseEntity<MutableIterable<Posts>>? {
        val findByTitle: MutableIterable<Posts> = postService.findAllByTitleWithQuerydsl(title)
        return ResponseEntity.ok(findByTitle)
    }

    @ApiOperation(value = "all post 조회", notes = "all post 조회")
    @GetMapping(value = ["/all"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun all(@RequestParam page:Int): ResponseEntity<Page<Posts>>? {
        val findAll = postService.findAllPost(page-1)
        return ResponseEntity.ok(findAll)
    }

    @ApiOperation(value = "post id 로 조회", notes = "post id 로 조회")
    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPostById(@PathVariable id: String): ResponseEntity<Posts> {
        val findById = postService.findPostById(id)
        return ResponseEntity.ok(findById)
    }

    @ApiOperation(value = "register post", notes = "register post")
    @PostMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun register(@RequestBody post: Post.PostRequest, request:HttpServletRequest): ResponseEntity<CommonResponse.BooleanResponse>? {
        return ResponseEntity.ok(postService.savePost(request, post))
    }

    @ApiOperation(value = "edit post", notes = "edit post")
    @PutMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun edit(@PathVariable id: String, @RequestBody post: Post.PostRequest, request:HttpServletRequest): ResponseEntity<CommonResponse.BooleanResponse>? {
        return ResponseEntity.ok(postService.editPost(request, post, id))
    }

    @ApiOperation(value = "delete post", notes = "delete post")
    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun delete(@PathVariable id: String, request:HttpServletRequest): ResponseEntity<CommonResponse.BooleanResponse>? {
        return ResponseEntity.ok(postService.deletePost(request, id))
    }

    @ApiOperation(value = "delete all post", notes = "delete all post")
    @DeleteMapping(value = ["/"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteAll(@RequestBody post: Post.DeleteList, request:HttpServletRequest): ResponseEntity<CommonResponse.BooleanResponse>? {
        return ResponseEntity.ok(postService.deleteAllPost(request,post.idList))
    }
}
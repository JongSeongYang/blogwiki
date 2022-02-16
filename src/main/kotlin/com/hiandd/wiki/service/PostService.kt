package com.hiandd.wiki.service

import com.hiandd.wiki.domain.Posts
import com.hiandd.wiki.repository.PostRepository
import com.hiandd.wiki.utils.Log
import org.springframework.stereotype.Service

@Service
class PostService(
        private val postRepository: PostRepository
) {
    companion object:Log{}

    fun findAllPost(): MutableList<Posts> {
        return postRepository.findAll()
    }

    fun findAllByTitle(title: String): MutableList<Posts> {
        return postRepository.findByTitleOrderByCreatedTimeDesc(title)
    }
}
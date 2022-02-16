package com.hiandd.wiki.repository

import com.hiandd.wiki.domain.Posts
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : MongoRepository<Posts, String> {
    fun findByTitleOrderByCreatedTimeDesc(title: String) : MutableList<Posts>

}
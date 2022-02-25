package com.hiandd.wiki.repository.post

import com.hiandd.wiki.domain.Posts
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : MongoRepository<Posts, String>, QuerydslPredicateExecutor<Posts> {
    fun findByTitleOrderByCreatedTimeDesc(title: String) : MutableList<Posts>

    fun findAllByDeletedTimeIsNullOrderByCreatedTimeDesc(pageable:Pageable) : Page<Posts>
}
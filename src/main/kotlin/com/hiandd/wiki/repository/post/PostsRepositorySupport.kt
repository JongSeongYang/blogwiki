package com.hiandd.wiki.repository.post

import com.hiandd.wiki.domain.Posts
import com.hiandd.wiki.domain.QPosts.posts
import com.hiandd.wiki.dto.Post
import com.mongodb.client.result.UpdateResult
import org.bson.types.ObjectId
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.ZoneOffset

@Repository
class PostsRepositorySupport(
        private val postRepository: PostRepository,
        private val mongoTemplate: MongoTemplate
) {

    fun findAllPostsByTitle(title: String): MutableIterable<Posts> {
        return postRepository
                .findAll(
                      posts.title.startsWith(title),
                        Sort.by("CreatedTime").descending()
                )
    }

    fun updatePost(postRequest: Post.PostRequest, id: String): UpdateResult {
        val query: Query = Query().addCriteria(Posts::_id isEqualTo ObjectId(id))
        val update: Update = Update()

        if (null != postRequest.contents)
            update.set("Contents", postRequest.contents)
        if (null != postRequest.title)
            update.set("Title", postRequest.title)
        if (null != postRequest.thumbnails)
            update.set("Thumbnails", postRequest.thumbnails)
        update.set("UpdatedTime", LocalDateTime.now(ZoneOffset.UTC))

        return mongoTemplate.updateFirst(query, update, "Posts")
    }

    fun deletePost(id: String): UpdateResult {
        val query: Query = Query().addCriteria(Posts::_id isEqualTo ObjectId(id))
                .addCriteria("DeletedTime" isEqualTo null)
        val update: Update = Update().set("DeletedTime",LocalDateTime.now(ZoneOffset.UTC))
                .set("UpdatedTime", LocalDateTime.now(ZoneOffset.UTC))
        return mongoTemplate.updateFirst(query, update, "Posts")
    }

    fun deleteAllPost(idList: List<String>): Long {
        var result: Long = 0
        idList.forEach() {
            val query: Query = Query().addCriteria(Posts::_id isEqualTo ObjectId(it))
//                    .addCriteria(Posts::deletedTime isEqualTo null)
            val update: Update = Update().set("DeletedTime",LocalDateTime.now(ZoneOffset.UTC))
                    .set("UpdatedTime", LocalDateTime.now(ZoneOffset.UTC))
            result += mongoTemplate.updateFirst(query, update, "Posts").modifiedCount
        }
        return result
    }
}
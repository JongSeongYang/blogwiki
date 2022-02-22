package com.hiandd.wiki.service

import com.hiandd.wiki.domain.Posts
import com.hiandd.wiki.dto.CommonResponse
import com.hiandd.wiki.dto.Post
import com.hiandd.wiki.enum.ErrorCode
import com.hiandd.wiki.errors.NoIdException
import com.hiandd.wiki.repository.post.PostRepository
import com.hiandd.wiki.repository.post.PostsRepositorySupport
import com.hiandd.wiki.utils.Log
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import javax.servlet.http.HttpServletRequest

@Service
class PostService(
        private val postRepository: PostRepository,
        private val postsRepositorySupport: PostsRepositorySupport
) {
    companion object:Log{}

    fun generatePageable(page: Int, pageSize: Int, vararg order: Sort.Order): PageRequest =
            PageRequest.of(page, pageSize, Sort.by(order.asList()))

    fun findAllPost(page:Int): Page<Posts> {
        return postRepository.findAll(generatePageable(page,10))
    }

    fun findAllByTitle(title: String): MutableList<Posts> {
        return postRepository.findByTitleOrderByCreatedTimeDesc(title)
    }

    fun findAllByTitleWithQuerydsl(title:String): MutableIterable<Posts> {
        return postsRepositorySupport.findAllPostsByTitle(title)
    }

    fun findPostById(id:String): Posts? {
        return postRepository.findById(id).orElse(null)
    }

    fun savePost(request: HttpServletRequest, postRequest: Post.PostRequest): CommonResponse.BooleanResponse {
        val posts = Posts(title = postRequest.title,
                contents = postRequest.contents,
                categoryObjectId = postRequest.categoryObjectId,
                writerObjectId = postRequest.writerIObjectId,
                thumbnails = postRequest.thumbnails
        )
        postRepository.insert(posts)
        return CommonResponse.BooleanResponse(true, "Post_Register_Success")
    }

    fun editPost(request: HttpServletRequest, postRequest: Post.PostRequest, id:String): CommonResponse.BooleanResponse {
        val updatePost = postsRepositorySupport.updatePost(postRequest, id)
        logger().info("updated count : "+ updatePost.modifiedCount)
        return CommonResponse.BooleanResponse(true, "Post_Edit_Success")
    }

    fun deletePost(request: HttpServletRequest, id:String): CommonResponse.BooleanResponse {
        val deletePost = postsRepositorySupport.deletePost(id).modifiedCount
        logger().info("Deleted count : $deletePost")
        return if(deletePost!= 0L)
            CommonResponse.BooleanResponse(true, "Post_Delete_Success")
        else
            CommonResponse.BooleanResponse(false, "Post_Delete_Fail")
    }

    fun deleteAllPost(request: HttpServletRequest, idList:List<String>): CommonResponse.BooleanResponse {
        val deletePost = postsRepositorySupport.deleteAllPost(idList)
        logger().info("Deleted count : $deletePost")
        return if(deletePost!= 0L)
            CommonResponse.BooleanResponse(true, "Post_Delete_Success")
        else
            CommonResponse.BooleanResponse(false, "Post_Delete_Fail")
    }
}
package com.hiandd.wiki.dto

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.hiandd.wiki.utils.TimeZoneDateSerializer
import java.time.LocalDateTime

class Post {

    data class PostRequest(
            var title: String? = null,
            var writerIObjectId: String? = null,
            var contents: String? = null,
            var companyObjectId: String? = null,
            var categoryObjectId: String? = null,
            var thumbnails : List<String>? = null
    )

    data class DeleteList(
            var idList: List<String>
    )
}
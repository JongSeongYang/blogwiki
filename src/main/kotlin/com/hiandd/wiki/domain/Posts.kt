package com.hiandd.wiki.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Document(collection="Posts")
data class Posts(
        @Field(name = "_id", targetType = FieldType.OBJECT_ID)
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var postId : String? = null,
        @Field(name = "Title")
        var title : String? = null,
        @Field(name = "WriterObjectId", targetType = FieldType.OBJECT_ID)
        var writerObjectId : String? = null,
        var name : String? = null,
        var contents : String? = null,
        var companyObjectId : String? = null,
        var categoryObjectId: String? = null,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var createdTime : ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC),
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var updatedTime : ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC),
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var deletedTime : ZonedDateTime? = null
)

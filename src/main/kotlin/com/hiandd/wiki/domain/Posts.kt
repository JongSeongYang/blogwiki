package com.hiandd.wiki.domain

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

        @Field(name = "Name")
        var name : String? = null,

        @Field(name = "Contents")
        var contents : String? = null,

        @Field(name = "CompanyObjectId")
        var companyObjectId : String? = null,

        @Field(name = "CategoryObjectId")
        var categoryObjectId: String? = null,

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @Field(name = "CreatedTime")
        var createdTime : ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC),

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @Field(name = "UpdatedTime")
        var updatedTime : ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC),

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        @Field(name = "DeletedTime")
        var deletedTime : ZonedDateTime? = null
)

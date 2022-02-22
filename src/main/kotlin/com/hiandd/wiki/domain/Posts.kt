package com.hiandd.wiki.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.hiandd.wiki.utils.TimeZoneDateSerializer
import com.querydsl.core.annotations.QueryEntity
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@QueryEntity
@Document(collection="Posts")
data class Posts(
        @Field(name = "_id", targetType = FieldType.OBJECT_ID)
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var _id : String? = null,

        @Field(name = "Title")
        var title : String? = null,

        @Field(name = "WriterObjectId", targetType = FieldType.OBJECT_ID)
        var writerObjectId : String? = null,

        @Field(name = "Contents")
        var contents : String? = null,

        @Field(name = "Type")
        var type : String? = null,

        @ElementCollection
        @Field(name = "Thumbnails")
        var thumbnails : List<String>? = mutableListOf(),

        @Field(name = "CompanyObjectId", targetType = FieldType.OBJECT_ID)
        var companyObjectId : String? = null,

        @Field(name = "CategoryObjectId", targetType = FieldType.OBJECT_ID)
        var categoryObjectId: String? = null,

        @JsonSerialize(using = TimeZoneDateSerializer::class)
        @Field(name = "CreatedTime", targetType = FieldType.TIMESTAMP)
        var createdTime : LocalDateTime? = LocalDateTime.now(ZoneOffset.UTC),

        @JsonSerialize(using = TimeZoneDateSerializer::class)
        @Field(name = "UpdatedTime", targetType = FieldType.TIMESTAMP)
        var updatedTime : LocalDateTime? = LocalDateTime.now(ZoneOffset.UTC),

        @JsonSerialize(using = TimeZoneDateSerializer::class)
        @Field(name = "DeletedTime", targetType = FieldType.TIMESTAMP)
        var deletedTime : LocalDateTime? = null
)

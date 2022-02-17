package com.hiandd.wiki.domain

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.hiandd.wiki.utils.TimeZoneDateSerializer
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Document(collection="Users")
data class Users(
        @Field(name = "_id", targetType = FieldType.OBJECT_ID)
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var userId : String? = null,

        @Field(name = "Email")
        var email : String? = null,

        @Field(name = "Password")
        var password : String? = null,

        @Field(name = "Profile")
        var profile : String? = null,

        @Field(name = "Name")
        var name : String? = null,

        @Field(name = "Role")
        var role : String? = null,

        @Field(name = "CompanyObjectId")
        var companyObjectId : String? = null,

        @Field(name = "LastChallengeTime", targetType = FieldType.TIMESTAMP)
        @JsonSerialize(using = TimeZoneDateSerializer::class)
        var lastChallengeTime : LocalDateTime? = null,

        @Field(name = "CreatedTime", targetType = FieldType.TIMESTAMP)
        @JsonSerialize(using = TimeZoneDateSerializer::class)
        var createdTime : LocalDateTime? = LocalDateTime.now(ZoneOffset.UTC),

        @Field(name = "UpdatedTime", targetType = FieldType.TIMESTAMP)
        @JsonSerialize(using = TimeZoneDateSerializer::class)
        var updatedTime : LocalDateTime? = LocalDateTime.now(ZoneOffset.UTC),

        @Field(name = "DeletedTime", targetType = FieldType.TIMESTAMP)
        @JsonSerialize(using = TimeZoneDateSerializer::class)
        var deletedTime : LocalDateTime? = null
)
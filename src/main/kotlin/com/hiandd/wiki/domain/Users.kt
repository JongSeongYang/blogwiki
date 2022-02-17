package com.hiandd.wiki.domain

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType
import org.springframework.format.annotation.DateTimeFormat
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

        @Field(name = "LastChallengeTime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var lastChallengeTime : ZonedDateTime? = null,

        @Field(name = "CreatedTime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var createdTime : ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC),

        @Field(name = "UpdatedTime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var updatedTime : ZonedDateTime? = ZonedDateTime.now(ZoneOffset.UTC),

        @Field(name = "DeletedTime")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        var deletedTime : ZonedDateTime? = null
)
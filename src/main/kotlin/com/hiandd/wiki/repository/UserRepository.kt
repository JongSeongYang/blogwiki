package com.hiandd.wiki.repository

import com.hiandd.wiki.domain.Users
import org.bson.types.ObjectId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<Users, String> {
    fun findByEmail(email: String): Users?
}
package com.hiandd.wiki.repository.user

import com.hiandd.wiki.domain.Users
import org.bson.types.ObjectId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: MongoRepository<Users, String>, QuerydslPredicateExecutor<Users> {
    fun findByEmail(email: String): Users?
}
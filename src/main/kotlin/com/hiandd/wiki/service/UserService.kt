package com.hiandd.wiki.service

import com.hiandd.wiki.domain.Users
import com.hiandd.wiki.repository.UserRepository
import com.hiandd.wiki.utils.Log
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {

    companion object : Log{}

    fun findUserByEmail(email:String): Users? {
       return userRepository.findByEmail(email)
    }

    fun findAllUserList(): MutableList<Users> {
        return userRepository.findAll()
    }

}
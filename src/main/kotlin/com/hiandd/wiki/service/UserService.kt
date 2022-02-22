package com.hiandd.wiki.service

import com.hiandd.wiki.domain.Users
import com.hiandd.wiki.repository.user.UserRepository
import com.hiandd.wiki.utils.Log
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
class UserService(
        private val userRepository: UserRepository
) {

    companion object : Log{}

    fun findUserByEmail(email:String): Users? {
       return userRepository.findByEmail(email)
    }

    fun findAllUserList(page: Int): Page<Users> {
        return userRepository.findAll(generatePageable(page,20,Sort.Order.desc("CreatedTime")))
    }

    fun generatePageable(page: Int, pageSize: Int, vararg order: Sort.Order): PageRequest =
            PageRequest.of(page, pageSize, Sort.by(order.asList()))
}
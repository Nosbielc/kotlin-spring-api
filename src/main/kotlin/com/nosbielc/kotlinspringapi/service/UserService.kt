package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.model.UserModel
import com.nosbielc.kotlinspringapi.repository.UserRepository
import org.springframework.stereotype.Service

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-22
 * @project kotlin-spring-api
 */
@Service
class UserService (
    val passwordService: PasswordService,
    val userRepository: UserRepository
) {

    fun createUser(username: String, rawPassword: String) {
        val salt = passwordService.generateSalt()
        val hashedPassword = passwordService.hashPassword(rawPassword, salt)

        val user = UserModel(
            username = username,
            password = hashedPassword,
            salt = salt
        )
        userRepository.save(user)
    }

    fun authenticateUser(username: String, rawPassword: String): Boolean {
        val user = userRepository.findByUsername(username) ?: return false
        return passwordService.isPasswordValid(rawPassword, user.salt, user.password)
    }
}
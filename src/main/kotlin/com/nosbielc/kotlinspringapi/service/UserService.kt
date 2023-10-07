package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.controller.request.RegisterRequest
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

    fun createUser(request: RegisterRequest) {
        val salt = passwordService.generateSalt()
        val hashedPassword = passwordService.hashPassword(request.password, salt)

        val user = UserModel(
            firstName = request.firstname,
            lastName = request.lastname,
            email = request.email,
            pass = hashedPassword,
            salt = salt
        )
        userRepository.save(user)
    }

    fun authenticateUser(email: String, rawPassword: String): Boolean {
        val user = userRepository.findByEmail(email) ?: return false
        return passwordService.isPasswordValid(rawPassword, user.salt, user.pass)
    }
}
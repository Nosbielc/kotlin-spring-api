package com.nosbielc.kotlinspringapi.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-20
 * @project kotlin-spring-api
 */
@Service
class PasswordService {

    private val passwordEncoder = BCryptPasswordEncoder()
    private val random = SecureRandom()

    fun hashPassword(password: String, salt: String): String {
        val saltedPassword = password + salt
        return passwordEncoder.encode(saltedPassword)
    }

    fun generateSalt(): String {
        val bytes = ByteArray(16)
        random.nextBytes(bytes)
        return Base64.getEncoder().encodeToString(bytes)
    }

    fun isPasswordValid(rawPassword: String, salt: String, hashedPassword: String): Boolean {
        val saltedPassword = rawPassword + salt
        return passwordEncoder.matches(saltedPassword, hashedPassword)
    }
}
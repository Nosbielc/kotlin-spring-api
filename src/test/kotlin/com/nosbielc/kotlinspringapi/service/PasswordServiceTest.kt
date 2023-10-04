package com.nosbielc.kotlinspringapi.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-21
 * @project kotlin-spring-api
 */
@SpringBootTest
class PasswordServiceTest {

    @Autowired
    private lateinit var passwordService: PasswordService

    private val rawPassword = "editorPass"
    private lateinit var salt: String
    private lateinit var hashedPassword: String

    @BeforeEach
    fun setUp() {
        salt = passwordService.generateSalt()

        hashedPassword = passwordService.hashPassword(rawPassword, salt)

        println("Pass: $hashedPassword")
        println("salt: $salt")
    }

    @Test
    fun testGenerateSalt() {
        val anotherSalt = passwordService.generateSalt()
        assertNotNull(salt)
        assertNotEquals(salt, anotherSalt)  // Check that two consecutively generated salts are different
    }

    @Test
    fun testHashPassword() {
        val passwordEncoder = BCryptPasswordEncoder()
        assertTrue(passwordEncoder.matches(rawPassword + salt, hashedPassword))
    }

    @Test
    fun testIsPasswordValid() {
        assertTrue(passwordService.isPasswordValid(rawPassword, salt, hashedPassword))
        assertFalse(passwordService.isPasswordValid("wrongPassword", salt, hashedPassword))
    }
}
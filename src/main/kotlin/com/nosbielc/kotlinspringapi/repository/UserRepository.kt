package com.nosbielc.kotlinspringapi.repository

import com.nosbielc.kotlinspringapi.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-22
 * @project kotlin-spring-api
 */
interface UserRepository : JpaRepository<UserModel, Long> {
    fun findByUsername(username: String): UserModel?
    fun findByEmail(email: String): UserModel?
}
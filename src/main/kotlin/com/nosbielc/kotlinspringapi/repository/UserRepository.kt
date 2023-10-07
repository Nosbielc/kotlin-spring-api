package com.nosbielc.kotlinspringapi.repository

import com.nosbielc.kotlinspringapi.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-22
 * @project kotlin-spring-api
 */
@Repository
interface UserRepository : JpaRepository<UserModel, Long> {
    fun findByEmail(email: String): UserModel?
}
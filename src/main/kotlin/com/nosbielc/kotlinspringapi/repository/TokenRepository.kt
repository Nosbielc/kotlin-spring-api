package com.nosbielc.kotlinspringapi.repository

import com.nosbielc.kotlinspringapi.model.TokenModel
import com.nosbielc.kotlinspringapi.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-22
 * @project kotlin-spring-api
 */
interface TokenRepository : JpaRepository<TokenModel, Long> {
    @Query("""
    select t from TokenModel t inner join UserModel u
    on t.user.id = u.id
    where u.id = :id and (t.expired = false or t.revoked = false)
""")
    fun findAllValidTokenByUser(id: Long): List<TokenModel>

    fun findByToken(token: String): Optional<TokenModel>
}
package com.nosbielc.kotlinspringapi.model

import com.nosbielc.kotlinspringapi.enums.TokenType
import jakarta.persistence.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */

@Entity
@Table(name = "tokens")
data class TokenModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    var token: String? = null,

    @Enumerated(EnumType.STRING)
    var tokenType: TokenType = TokenType.BEARER,

    var revoked: Boolean = false,

    var expired: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: UserModel? = null
) {
}
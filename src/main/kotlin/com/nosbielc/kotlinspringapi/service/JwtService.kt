package com.nosbielc.kotlinspringapi.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */
@Service
class JwtService {

    @Value("\${application.security.jwt.secret-key}")
    lateinit var secretKey: String

    @Value("\${application.security.jwt.expiration}")
    var jwtExpiration: Long = 0

    @Value("\${application.security.jwt.refresh-token.expiration}")
    var refreshExpiration: Long = 0

    fun extractUsername(token: String): String =
        extractClaim(token, Claims::getSubject)

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T =
        claimsResolver.invoke(extractAllClaims(token))

    fun generateToken(userDetails: UserDetails): String =
        generateToken(mapOf(), userDetails)

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): String =
        buildToken(extraClaims, userDetails, jwtExpiration)

    fun generateRefreshToken(userDetails: UserDetails): String =
        buildToken(mapOf(), userDetails, refreshExpiration)

    private fun buildToken(
        extraClaims: Map<String, Any>,
        userDetails: UserDetails,
        expiration: Long
    ): String =
        Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean =
        extractExpiration(token).before(Date())

    private fun extractExpiration(token: String): Date =
        extractClaim(token, Claims::getExpiration)

    private fun extractAllClaims(token: String): Claims =
        Jwts.parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .body

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
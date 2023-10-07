package com.nosbielc.kotlinspringapi.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.nosbielc.kotlinspringapi.controller.request.AuthenticationRequest
import com.nosbielc.kotlinspringapi.controller.request.AuthenticationResponse
import com.nosbielc.kotlinspringapi.controller.request.RegisterRequest
import com.nosbielc.kotlinspringapi.model.TokenModel
import com.nosbielc.kotlinspringapi.model.UserModel
import com.nosbielc.kotlinspringapi.repository.RoleRepository
import com.nosbielc.kotlinspringapi.repository.TokenRepository
import com.nosbielc.kotlinspringapi.repository.UserRepository
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */
@Service
class AuthenticationService (
    val userRepository: UserRepository,
    val tokenRepository: TokenRepository,
    val roleRepository: RoleRepository,
    val passwordService: PasswordService,
    val jwtService: JwtService,
    val authenticationManager: AuthenticationManager
) {

    fun register(request: RegisterRequest): AuthenticationResponse {
        val roleDefault = setOf( roleRepository.findByName("USER")!! )

        val salt =  passwordService.generateSalt()
        val passWord = passwordService.hashPassword(request.password, salt)
        val user = UserModel(null,
            firstName = request.firstname,
            lastName = request.lastname,
            email = request.email,
            pass = passWord,
            salt = salt,
            roles = roleDefault
            )

        val savedUser = userRepository.save(user)
        val jwtToken = jwtService.generateToken(savedUser)
        val refreshToken = jwtService.generateRefreshToken(savedUser)
        saveUserToken(savedUser, jwtToken)

        return AuthenticationResponse(accessToken = jwtToken, refreshToken = refreshToken)
    }

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )

        val user = userRepository.findByEmail(request.email!!)!!
        val jwtToken = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)

        revokeAllUserTokens(user)
        saveUserToken(user, jwtToken)

        return AuthenticationResponse(accessToken = jwtToken, refreshToken = refreshToken)
    }

    fun saveUserToken(user: UserModel, jwtToken: String) {
        val token = TokenModel(token = jwtToken, user = user)

        tokenRepository.save(token)
    }

    fun revokeAllUserTokens(user: UserModel) {
        val validUserTokens = tokenRepository.findAllValidTokenByUser(user.id!!)
        if (validUserTokens.isEmpty()) return

        validUserTokens.forEach { token ->
            token.expired = true
            token.revoked = true
        }

        tokenRepository.saveAll(validUserTokens)
    }

    fun refreshToken(request: HttpServletRequest, response: HttpServletResponse) {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) return

        val refreshToken = authHeader.substring(7)
        val userEmail = jwtService.extractUsername(refreshToken)

        userEmail?.let {
            val user = userRepository.findByEmail(it)
            if (jwtService.isTokenValid(refreshToken, user!!)) {
                val accessToken = jwtService.generateToken(user)
                revokeAllUserTokens(user)
                saveUserToken(user, accessToken)

                val authResponse = AuthenticationResponse(accessToken = accessToken, refreshToken = refreshToken)

                ObjectMapper().writeValue(response.outputStream, authResponse)
            }
        }
    }
}
package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.AuthenticationRequest
import com.nosbielc.kotlinspringapi.controller.request.AuthenticationResponse
import com.nosbielc.kotlinspringapi.controller.request.RegisterRequest
import com.nosbielc.kotlinspringapi.service.AuthenticationService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController (
    val authenticationService: AuthenticationService
) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid registerRequest : RegisterRequest) :
            ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.register(registerRequest))
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody @Valid authenticationRequest: AuthenticationRequest) :
            ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest))
    }

    @PostMapping("/refresh-token")
    @Throws(IOException::class)
    fun refreshToken(
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        authenticationService.refreshToken(request, response)
    }
}
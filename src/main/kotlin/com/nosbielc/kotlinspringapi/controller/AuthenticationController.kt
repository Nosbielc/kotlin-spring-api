package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.service.AuthenticationService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController (
    val authenticationService: AuthenticationService
) {
}
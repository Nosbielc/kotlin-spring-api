package com.nosbielc.kotlinspringapi.controller.request

import com.fasterxml.jackson.annotation.JsonAlias

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */
data class AuthenticationResponse(
    @JsonAlias("access_token")
    var accessToken: String? = null,
    @JsonAlias("refresh_token")
    private val refreshToken: String? = null
)

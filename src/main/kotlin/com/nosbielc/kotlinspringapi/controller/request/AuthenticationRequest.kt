package com.nosbielc.kotlinspringapi.controller.request

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */
data class AuthenticationRequest(
    var email: String? = null,
    var password: String? = null)

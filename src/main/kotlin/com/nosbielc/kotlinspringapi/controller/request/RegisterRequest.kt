package com.nosbielc.kotlinspringapi.controller.request

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */
data class RegisterRequest(
    var firstname: String,
    var lastname: String,
    var email: String,
    var password: String,
    var role: String
)
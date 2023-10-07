package com.nosbielc.kotlinspringapi.controller.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */
data class RegisterRequest(
    @NotNull
    var firstname: String,
    @NotNull
    var lastname: String,
    @Email
    var email: String,
    @NotNull
    var password: String,
    var role: String
)
package com.nosbielc.kotlinspringapi.controller.request

import com.nosbielc.kotlinspringapi.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.hibernate.validator.constraints.Length

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
data class PostCustomerRequest (

    @field:NotEmpty
    @field:Length(max = 255)
    var name : String,

    @field:Email(message = "Email not valid")
    @EmailAvailable
    var email : String

)
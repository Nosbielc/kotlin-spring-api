package com.nosbielc.kotlinspringapi.controller.response

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-16
 * @project kotlin-spring-api
 */
data class CustomerResponse (
    var id : Int? = null,
    var name : String,
    var email : String
)

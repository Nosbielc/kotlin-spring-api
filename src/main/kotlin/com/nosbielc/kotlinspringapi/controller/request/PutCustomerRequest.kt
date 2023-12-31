package com.nosbielc.kotlinspringapi.controller.request

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
data class PutCustomerRequest (
    var name : String,
    var email : String
)
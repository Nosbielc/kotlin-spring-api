package com.nosbielc.kotlinspringapi.controller.response

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-16
 * @project kotlin-spring-api
 */
data class ErrorResponse(
    var httpStatus: Int,
    var message: String,
    var internalCode: String = "0001",
    var errors: List<FieldErrorResponse>?
)

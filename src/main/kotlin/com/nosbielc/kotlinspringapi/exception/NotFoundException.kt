package com.nosbielc.kotlinspringapi.exception

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-16
 * @project kotlin-spring-api
 */
class NotFoundException(
    override val message: String,
    val errorCode: String
): Exception() {
}
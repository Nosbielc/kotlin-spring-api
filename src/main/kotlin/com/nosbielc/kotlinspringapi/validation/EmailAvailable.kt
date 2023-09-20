package com.nosbielc.kotlinspringapi.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-18
 * @project kotlin-spring-api
 */

@Constraint( validatedBy = [ EmailAvailableValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvailable(
    val message: String = "email already in use!",
    val groups : Array<KClass<*>> = [],
    val payload : Array<KClass<out Payload>> = []
)

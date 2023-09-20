package com.nosbielc.kotlinspringapi.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length
import java.math.BigDecimal

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
data class PostBookRequest (

    @field:NotEmpty
    @field:Length(max = 255)
    var name : String,

    @field:NotNull
    @field:DecimalMin(value = "0.0", inclusive = false)
    @field:Digits(integer=6, fraction=2)
    var price : BigDecimal,

    @field:NotNull
    @JsonAlias("customer_id")
    var customerId: Int
)
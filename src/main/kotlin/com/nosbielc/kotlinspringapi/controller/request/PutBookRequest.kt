package com.nosbielc.kotlinspringapi.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import java.math.BigDecimal

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
data class PutBookRequest (
    var name : String?,
    var price : BigDecimal?,
    @JsonAlias("customer_id")
    var customerId: Int
)
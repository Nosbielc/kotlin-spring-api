package com.nosbielc.kotlinspringapi.controller.response

import com.nosbielc.kotlinspringapi.enums.BookStatus
import com.nosbielc.kotlinspringapi.model.CustomerModel
import java.math.BigDecimal

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-16
 * @project kotlin-spring-api
 */
data class BookResponse(
    var id : Int? = null,
    var name : String,
    var price : BigDecimal,
    var status : BookStatus,
    var customer: CustomerModel
)

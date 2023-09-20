package com.nosbielc.kotlinspringapi.extension

import com.nosbielc.kotlinspringapi.controller.request.PostBookRequest
import com.nosbielc.kotlinspringapi.controller.request.PostCustomerRequest
import com.nosbielc.kotlinspringapi.controller.request.PutBookRequest
import com.nosbielc.kotlinspringapi.controller.request.PutCustomerRequest
import com.nosbielc.kotlinspringapi.controller.response.BookResponse
import com.nosbielc.kotlinspringapi.controller.response.CustomerResponse
import com.nosbielc.kotlinspringapi.enums.BookStatus
import com.nosbielc.kotlinspringapi.model.BookModel
import com.nosbielc.kotlinspringapi.model.CustomerModel

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-15
 * @project kotlin-spring-api
 */

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id : Int): CustomerModel {
    return CustomerModel(id,  this.name, this.email)
}

fun PostBookRequest.toBookModel(customerParam : CustomerModel) : BookModel {
    return BookModel(name = this.name, price = this.price, status = BookStatus.ACTIVE, customer = customerParam)
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name?: previousValue.name,
        price = this.price?: previousValue.price,
        customer = previousValue.customer,
        status = previousValue.status
    )
}

fun BookModel.toResponse() : BookResponse = BookResponse(
    id, name, price, status, customer
)

fun CustomerModel.toResponse() : CustomerResponse = CustomerResponse(
    id, name, email
)
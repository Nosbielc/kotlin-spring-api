package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.PostBookRequest
import com.nosbielc.kotlinspringapi.controller.request.PostCustomerRequest
import com.nosbielc.kotlinspringapi.controller.request.PutCustomerRequest
import com.nosbielc.kotlinspringapi.extension.toBookModel
import com.nosbielc.kotlinspringapi.extension.toCustomerModel
import com.nosbielc.kotlinspringapi.model.BookModel
import com.nosbielc.kotlinspringapi.model.CustomerModel
import com.nosbielc.kotlinspringapi.service.BookService
import com.nosbielc.kotlinspringapi.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/book")
class BookController (
    val bookService: BookService,
    val customerService: CustomerService
){

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCostumer(@RequestBody postBookRequest: PostBookRequest) : ResponseEntity<BookModel> {
        val customer = customerService.getCostumer(postBookRequest.customerId);

        return ResponseEntity(bookService.create(postBookRequest.toBookModel(customer)), HttpStatus.CREATED)
    }

}
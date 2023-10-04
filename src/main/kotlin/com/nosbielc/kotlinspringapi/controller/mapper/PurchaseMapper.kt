package com.nosbielc.kotlinspringapi.controller.mapper

import com.nosbielc.kotlinspringapi.controller.request.PostPurchaseRequest
import com.nosbielc.kotlinspringapi.model.PurchaseModel
import com.nosbielc.kotlinspringapi.service.BookService
import com.nosbielc.kotlinspringapi.service.CustomerService
import org.springframework.stereotype.Component

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-19
 * @project kotlin-spring-api
 */
@Component
class PurchaseMapper(
    val bookService: BookService,
    val customerService: CustomerService
) {

    fun toModel(request : PostPurchaseRequest ) : PurchaseModel {
        val customer = this.customerService.getCostumer(request.customerId)
        val books = this.bookService.findAllByIds(request.booksIds)

        return PurchaseModel(
            customer = customer,
            book = books.toMutableList(),
            nfe = null,
            price = books.sumOf { it.price }
        )
    }

}
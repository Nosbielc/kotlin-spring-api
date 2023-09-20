package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.PostBookRequest
import com.nosbielc.kotlinspringapi.controller.response.BookResponse
import com.nosbielc.kotlinspringapi.extension.toBookModel
import com.nosbielc.kotlinspringapi.extension.toResponse
import com.nosbielc.kotlinspringapi.service.BookService
import com.nosbielc.kotlinspringapi.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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
    fun postCostumer(@RequestBody @Valid postBookRequest: PostBookRequest) : ResponseEntity<BookResponse> {
        val customer = customerService.getCostumer(postBookRequest.customerId);

        return ResponseEntity(bookService.create(postBookRequest.toBookModel(customer)).toResponse(), HttpStatus.CREATED)
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = ["id"]) pageable: Pageable) : ResponseEntity<Page<BookResponse>> {
        return ResponseEntity.ok(bookService.findAll(
            pageable
        ).map { it.toResponse() });
    }

    @GetMapping("/{id}")
    fun getBook(@PathVariable id : Int) : ResponseEntity<BookResponse> {
        return ResponseEntity.ok(bookService.getBook(id)!!.toResponse())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id : Int) {
        bookService.delete(id)
    }

}
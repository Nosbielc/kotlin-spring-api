package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.model.BookModel
import com.nosbielc.kotlinspringapi.repository.BookRepository
import org.springframework.stereotype.Service

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
@Service
class BookService (
   val bookRepository: BookRepository
)  {

    fun create(bookModel: BookModel) : BookModel {
        return this.bookRepository.save(bookModel);
    }

}
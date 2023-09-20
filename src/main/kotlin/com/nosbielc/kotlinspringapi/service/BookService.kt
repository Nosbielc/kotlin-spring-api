package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.enums.BookStatus
import com.nosbielc.kotlinspringapi.enums.Errors
import com.nosbielc.kotlinspringapi.exception.NotFoundException
import com.nosbielc.kotlinspringapi.model.BookModel
import com.nosbielc.kotlinspringapi.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
@Service
class BookService (
    val bookRepository: BookRepository
)  {

    fun create(bookModel: BookModel) : BookModel = this.bookRepository.save(bookModel)
    fun findAll(pageable: Pageable): Page<BookModel> = this.bookRepository.findAll(pageable)
    fun getBook(id: Int): BookModel? = this.bookRepository.findById(id).orElseThrow {
        NotFoundException(Errors.KSA20001.message.format(id), Errors.KSA20001.code)
    }
    fun delete(id: Int) {

        if (!this.bookRepository.existsById(id))
            throw NotFoundException(Errors.KSA20001.message.format(id), Errors.KSA20001.code);

        this.bookRepository.deleteById(id);
    }
    fun updateStatus(idBook: Int, bookStatus: BookStatus) {
        val book = this.getBook(idBook)

        book!!.status = bookStatus

        this.bookRepository.save(book)
    }
}
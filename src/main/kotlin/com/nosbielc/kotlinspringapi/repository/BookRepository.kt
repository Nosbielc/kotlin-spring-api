package com.nosbielc.kotlinspringapi.repository

import com.nosbielc.kotlinspringapi.model.BookModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-15
 * @project kotlin-spring-api
 */

@Repository
interface BookRepository : CrudRepository<BookModel, Int> {
}
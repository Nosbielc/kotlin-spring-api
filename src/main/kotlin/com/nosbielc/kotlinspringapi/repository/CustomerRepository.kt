package com.nosbielc.kotlinspringapi.repository

import com.nosbielc.kotlinspringapi.model.CustomerModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-15
 * @project kotlin-spring-api
 */

@Repository
interface CustomerRepository : JpaRepository<CustomerModel, Int> {
    fun existsByEmail(email : String) : Boolean
}
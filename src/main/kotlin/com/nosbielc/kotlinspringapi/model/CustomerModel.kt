package com.nosbielc.kotlinspringapi.model

import jakarta.persistence.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
@Entity
@Table(name = "customer")
data class CustomerModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @Column(length = 255, nullable = false)
    var name : String,

    @Column(length = 255, nullable = false, unique = true)
    var email : String
)
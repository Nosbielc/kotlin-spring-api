package com.nosbielc.kotlinspringapi.model

import com.nosbielc.kotlinspringapi.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
@Entity
@Table(name = "book")
data class BookModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @Column(length = 255, nullable = false)
    var name : String,

    @Column(nullable = false)
    var price : BigDecimal,

    @Column
    @Enumerated(EnumType.ORDINAL)
    var status : BookStatus,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel

)
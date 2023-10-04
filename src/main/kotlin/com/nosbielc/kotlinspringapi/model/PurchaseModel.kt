package com.nosbielc.kotlinspringapi.model

import jakarta.persistence.*
import org.apache.commons.lang3.mutable.Mutable
import java.math.BigDecimal
import java.time.LocalDateTime

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-19
 * @project kotlin-spring-api
 */
@Entity
@Table(name = "purchase")
data class PurchaseModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel,

    @ManyToMany
    @JoinTable(name = "purchase_book",
        joinColumns = [JoinColumn(name = "customer_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")])
    var book: MutableList<BookModel>,

    @Column
    var nfe: String? = null,

    @Column
    var price: BigDecimal,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
)

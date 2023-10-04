package com.nosbielc.kotlinspringapi.model

import jakarta.persistence.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-20
 * @project kotlin-spring-api
 */
@Entity
@Table(name = "roles")
data class RoleModel(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String

)

package com.nosbielc.kotlinspringapi.repository

import com.nosbielc.kotlinspringapi.model.RoleModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-22
 * @project kotlin-spring-api
 */
@Repository
interface RoleRepository : JpaRepository<RoleModel, Long> {
    fun findByName(name: String): RoleModel?
}
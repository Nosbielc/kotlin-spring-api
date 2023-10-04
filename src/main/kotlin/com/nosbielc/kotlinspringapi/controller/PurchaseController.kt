package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.mapper.PurchaseMapper
import com.nosbielc.kotlinspringapi.controller.request.PostPurchaseRequest
import com.nosbielc.kotlinspringapi.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-19
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/purchase")
class PurchaseController(
    val purchaseService: PurchaseService,
    val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postPurchase(@RequestBody request : PostPurchaseRequest) : ResponseEntity<String> {
        this.purchaseService.create(purchaseMapper.toModel(request))
        return ResponseEntity.status(HttpStatus.CREATED).body("OK")
    }

}
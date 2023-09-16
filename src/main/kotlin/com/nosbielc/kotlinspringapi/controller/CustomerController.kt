package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.PostCustomerRequest
import com.nosbielc.kotlinspringapi.controller.request.PutCustomerRequest
import com.nosbielc.kotlinspringapi.extension.toCustomerModel
import com.nosbielc.kotlinspringapi.model.CustomerModel
import com.nosbielc.kotlinspringapi.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/costumer")
class CustomerController (
    val customerService: CustomerService
){

    @GetMapping
    fun getAll() : ResponseEntity<List<CustomerModel>> {
        return ResponseEntity.ok(customerService.getAllCostumers())
    }

    @GetMapping("/{id}")
    fun getCostumer(@PathVariable id : Int) : ResponseEntity<CustomerModel> {
        return ResponseEntity.ok(customerService.getCostumer(id))
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun putCostumer(@PathVariable id : Int, @RequestBody putCostumer : PutCustomerRequest) {
        customerService.update(putCostumer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCostumer(@PathVariable id : Int) {
        customerService.delete(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCostumer(@RequestBody postCostumer : PostCustomerRequest) : ResponseEntity<CustomerModel> {
        return ResponseEntity(customerService.create(postCostumer.toCustomerModel()), HttpStatus.CREATED)
    }

}
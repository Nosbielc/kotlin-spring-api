package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.PostCustomerRequest
import com.nosbielc.kotlinspringapi.controller.request.PutCustomerRequest
import com.nosbielc.kotlinspringapi.controller.response.CustomerResponse
import com.nosbielc.kotlinspringapi.extension.toCustomerModel
import com.nosbielc.kotlinspringapi.extension.toResponse
import com.nosbielc.kotlinspringapi.service.CustomerService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
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

    @Operation(summary = "Get all Customers")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Found Customers", content = [
            (Content(mediaType = "application/json", array = (
                    ArraySchema(schema = Schema(implementation = Page::class)))))]),
        ApiResponse(responseCode = "400", description = "Bad request", content = [Content()]),
        ApiResponse(responseCode = "404", description = "Did not find any Customers", content = [Content()])]
    )
    @GetMapping
    fun getAll(@PageableDefault(page = 0, size = 10, direction = Sort.Direction.ASC, sort = ["id"]) pageable: Pageable) : ResponseEntity<Page<CustomerResponse>> {
        return ResponseEntity.ok(customerService.getAllCostumers(
            pageable
        ).map { it.toResponse() })
    }

    @GetMapping("/{id}")
    fun getCostumer(@PathVariable id : Int) : ResponseEntity<CustomerResponse> {
        return ResponseEntity.ok(customerService.getCostumer(id).toResponse())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun putCostumer(@PathVariable id : Int, @RequestBody @Valid putCostumer : PutCustomerRequest) {
        customerService.update(putCostumer.toCustomerModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCostumer(@PathVariable id : Int) {
        customerService.delete(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCostumer(@RequestBody @Valid postCostumer : PostCustomerRequest) : ResponseEntity<CustomerResponse> {
        return ResponseEntity(customerService.create(postCostumer.toCustomerModel()).toResponse(), HttpStatus.CREATED)
    }

}
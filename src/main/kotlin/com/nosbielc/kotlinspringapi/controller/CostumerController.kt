package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.CostumerModelRequest
import com.nosbielc.kotlinspringapi.model.CostumerModel
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/costumer")
class CostumerController {

    @GetMapping
    fun getCostumer() : CostumerModel {
        return CostumerModel("1", "Nosbielc", "nosbielc@email.com")
    }


    @PostMapping
    fun postCostumer(@RequestBody costumer : CostumerModelRequest) : CostumerModelRequest {
        return  costumer;
    }

    @GetMapping("/{costumerName}")
    fun helloWord2(@PathVariable costumerName: String) : String {
        return "Hello Word for $costumerName !!"
    }



}
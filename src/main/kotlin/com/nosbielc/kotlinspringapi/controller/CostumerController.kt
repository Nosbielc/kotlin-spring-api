package com.nosbielc.kotlinspringapi.controller

import com.nosbielc.kotlinspringapi.controller.request.PostCostumerRequest
import com.nosbielc.kotlinspringapi.model.CostumerModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */

@RestController
@RequestMapping("/costumer")
class CostumerController {

    val costumers = mutableListOf<CostumerModel>()

    @GetMapping
    fun getAll() : List<CostumerModel> {
        return costumers
    }

    @GetMapping("/{id}")
    fun getCostumer(@PathVariable id : String) : CostumerModel {
        return costumers.first { it.id == id }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun postCostumer(@RequestBody postCostumer : PostCostumerRequest) : CostumerModel {

        val id = if (costumers.isEmpty()) {
            1
        } else {
            costumers.last().id.toInt() + 1
        }.toString()

        var costumer = CostumerModel(id, postCostumer.name, postCostumer.email)

        costumers.add(costumer)

        return costumer;
    }

}
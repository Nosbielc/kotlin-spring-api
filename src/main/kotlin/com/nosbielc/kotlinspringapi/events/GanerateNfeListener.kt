package com.nosbielc.kotlinspringapi.events

import com.nosbielc.kotlinspringapi.repository.PurchaseRepository
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-19
 * @project kotlin-spring-api
 */

@Component
class GanerateNfeListener(
    val purchaseRepository: PurchaseRepository
)
{

    @Async
    @EventListener
    fun listener(purchaseEvent: PurchaseEvent){
        val nfe = UUID.randomUUID().toString()

        val purchase = purchaseEvent.purchaseModel.copy(nfe = nfe)

        this.purchaseRepository.save(purchase)
    }


}
package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.events.PurchaseEvent
import com.nosbielc.kotlinspringapi.model.PurchaseModel
import com.nosbielc.kotlinspringapi.repository.PurchaseRepository
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    val purchaseRepository: PurchaseRepository,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun create(purchaseModel: PurchaseModel) {
        val purchase = this.purchaseRepository.save(purchaseModel)

        this.applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }
}

package com.nosbielc.kotlinspringapi.events

import com.nosbielc.kotlinspringapi.model.PurchaseModel
import org.springframework.context.ApplicationEvent

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-19
 * @project kotlin-spring-api
 */
class PurchaseEvent(
    source: Any,
    val purchaseModel: PurchaseModel
) :ApplicationEvent(source)
package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.enums.Errors
import com.nosbielc.kotlinspringapi.exception.NotFoundException
import com.nosbielc.kotlinspringapi.model.CustomerModel
import com.nosbielc.kotlinspringapi.repository.CustomerRepository
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
@Service
class CustomerService (
    val customerRepository: CustomerRepository
)  {


    fun getAllCostumers(pageable: Pageable) : Page<CustomerModel> {
        return this.customerRepository.findAll(pageable);
    }

    fun getCostumer(id : Int) : CustomerModel {
        return this.customerRepository.findById(id).orElseThrow {
            NotFoundException(Errors.KSA10001.message.format(id), Errors.KSA10001.code)
        }
    }

    fun update(customer : CustomerModel) {

        if (!this.customerRepository.existsById(customer.id!!)) {
            throw Exception();
        }

        this.customerRepository.save(customer);

    }

    fun delete(id : Int) {

        if (!this.customerRepository.existsById(id)) {
            throw NotFoundException(Errors.KSA10001.message.format(id), Errors.KSA10001.code);
        }

        this.customerRepository.deleteById(id);
    }

    fun create(customerModel : CustomerModel) : CustomerModel {
        val costumer = CustomerModel(name = customerModel.name, email = customerModel.email)
        return this.customerRepository.save(costumer);
    }

    fun emailAvailable(email: String): Boolean {
        return !this.customerRepository.existsByEmail(email)
    }

}
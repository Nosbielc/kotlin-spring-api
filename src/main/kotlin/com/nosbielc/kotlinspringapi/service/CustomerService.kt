package com.nosbielc.kotlinspringapi.service

import com.nosbielc.kotlinspringapi.model.CustomerModel
import com.nosbielc.kotlinspringapi.repository.CustomerRepository
import org.springframework.stereotype.Service

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-13
 * @project kotlin-spring-api
 */
@Service
class CustomerService (
   val customerRepository: CustomerRepository
)  {

    fun getAllCostumers() : List<CustomerModel> {
        return this.customerRepository.findAll().toList();
    }

    fun getCostumer(id : Int) : CustomerModel {
        return this.customerRepository.findById(id).orElse(CustomerModel(null, "", ""))
    }

    fun update(customer : CustomerModel) {

        if (!this.customerRepository.existsById(customer.id!!)) {
            throw Exception();
        }

        this.customerRepository.save(customer);

    }

    fun delete(id : Int) {

        if (!this.customerRepository.existsById(id)) {
            throw Exception();
        }

        this.customerRepository.deleteById(id);
    }

    fun create(customerModel : CustomerModel) : CustomerModel {
        val costumer = CustomerModel(name = customerModel.name, email = customerModel.email)
        return this.customerRepository.save(costumer);
    }

}
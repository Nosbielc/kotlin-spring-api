package com.nosbielc.kotlinspringapi.enums

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-16
 * @project kotlin-spring-api
 */
enum class Errors(val code: String, val message: String) {

    /**
     *  Generic
     */
    KSA00001("KSA-00001", "Invalid Request"),
    KSA00002("KSA-00002", "Invalid Request - SQL"),

    /**
     *   Errors messages domain Customer 1xxxxx
     */
    KSA10001("KSA-10001","Customer [%s] not exists"),


    /**
     *   Errors messages domain Book 2xxxxx
     */
    KSA20001("KSA-20001","Book [%s] not exists")

}
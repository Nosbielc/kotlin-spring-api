package com.nosbielc.kotlinspringapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("dev")
class SecurityConfigurationDev {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests { authorize ->
            authorize.anyRequest().permitAll();
        }.build()
    }
}
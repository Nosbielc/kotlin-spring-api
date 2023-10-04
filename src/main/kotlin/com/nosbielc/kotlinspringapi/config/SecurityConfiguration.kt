package com.nosbielc.kotlinspringapi.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutHandler

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-25
 * @project kotlin-spring-api
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration (
    val jwtAuthFilter: JwtAuthenticationFilter,
    val authenticationProvider: AuthenticationProvider,
    val logoutHandler: LogoutHandler ){

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        return http.authorizeHttpRequests { authorize ->

            authorize.requestMatchers(
                "/api/v1/auth/**",
                "/v2/api-docs",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**",
                "/swagger-ui.html"
            ).permitAll()

//        authorize.requestMatchers(toH2Console()).permitAll()

//            authorize.requestMatchers(PATH_ADMIN + "**").hasRole(ADMIN.name)
//            authorize.requestMatchers(PATH_MANAGEMENT + "**").hasAnyRole(ADMIN.name, MANAGER.name)
//
//            authorize.requestMatchers(GET, PATH_MANAGEMENT + "**").hasAnyAuthority(ADMIN_READ.name, MANAGER_READ.name)
//            authorize.requestMatchers(POST, PATH_MANAGEMENT + "**").hasAnyAuthority(ADMIN_CREATE.name, MANAGER_CREATE.name)
//            authorize.requestMatchers(PUT, PATH_MANAGEMENT + "**").hasAnyAuthority(ADMIN_UPDATE.name, MANAGER_UPDATE.name)
//            authorize.requestMatchers(DELETE, PATH_MANAGEMENT + "**").hasAnyAuthority(ADMIN_DELETE.name, MANAGER_DELETE.name)

            authorize.anyRequest().authenticated()
        }
            .headers { headers -> headers.frameOptions(Customizer { it.sameOrigin() }) }
            .csrf(AbstractHttpConfigurer<*, *>::disable)
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(this.jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .logout { logout ->
                logout.logoutSuccessUrl("/api/v1/auth/logout")
                logout.addLogoutHandler(logoutHandler)
                logout.logoutSuccessHandler { request, response, authentication -> SecurityContextHolder.clearContext() }
            }
            .build()
    }
}
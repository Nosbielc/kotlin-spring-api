package com.nosbielc.kotlinspringapi.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

/**
 * @author Cleibson Gomes (https://github.com/Nosbielc) ON 2023-09-20
 * @project kotlin-spring-api
 */
@Entity
@Table(name = "users")
data class UserModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var salt: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = false)
    var enabled: Boolean = true,

    @Column(nullable = false)
    var expired: Boolean = false,

    @Column(nullable = false)
    var locked: Boolean = false,

    @Column(name = "credentials_expired", nullable = false)
    var credentialsExpired: Boolean = false,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<RoleModel> = HashSet()
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles.map { SimpleGrantedAuthority(it.name) }.toMutableList()
    }

    override fun getPassword(): String {
        return "${this.password} and ${this.salt}"
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        return !this.expired
    }

    override fun isAccountNonLocked(): Boolean {
        return !this.locked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return !this.credentialsExpired
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }
}

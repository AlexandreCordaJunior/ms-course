package com.ale.hroauth.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import java.util.stream.Collectors

data class User constructor(
    var id: Long,
    var name: String,
    var email: String,
    private var password: String
): Serializable, UserDetails {
    var roles: Set<Role> = mutableSetOf()
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return roles.stream().map { SimpleGrantedAuthority(it.roleName) }
            .collect(Collectors.toList())
    }

    override fun getPassword(): String = password

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
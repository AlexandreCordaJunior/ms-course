package com.ale.hroauth.service

import com.ale.hroauth.domain.User
import com.ale.hroauth.feignClient.UserFeignClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class UserService @Autowired constructor(
    private val client: UserFeignClient,
    private val logger: Logger = LoggerFactory.getLogger(UserService::class.java)
): UserDetailsService {

    fun findByEmail(email: String): User {
        val user = client.getByEmail(email).body
        if(user == null) {
            logger.error("Email not found: $email")
            throw IllegalArgumentException("Email not found")
        }
        logger.info("Email found: $email")
        return user
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("entrou em loadByUsername")
        if(username == null) {
            throw UsernameNotFoundException("Username not found")
        }
        logger.info("username != null")
        val user = client.getByEmail(username).body
        if(user == null) {
            logger.error("Email not found: $username")
            throw UsernameNotFoundException("Email not found")
        }
        return user
    }

}
package com.ale.hroauth.resource

import com.ale.hroauth.domain.User
import com.ale.hroauth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserResource @Autowired constructor(
    private val service: UserService
) {
    @GetMapping("/search")
    fun findByEmail(@RequestParam email: String): ResponseEntity<User> {
        return try {
            val user = service.findByEmail(email)
            ResponseEntity.ok(user)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}
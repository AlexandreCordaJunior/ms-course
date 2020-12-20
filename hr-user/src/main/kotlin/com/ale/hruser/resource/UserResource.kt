package com.ale.hruser.resource

import com.ale.hruser.domain.User
import com.ale.hruser.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserResource @Autowired constructor(val service: UserService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<User> {
        val user = service.getById(id).orElse(null)
        return if (user == null) {
            ResponseEntity.notFound().build()
        }
        else {
            ResponseEntity.ok(user)
        }
    }

    @GetMapping("/search")
    fun getByEmail(@RequestParam email: String): ResponseEntity<User> {
        val user = service.getByEmail(email).orElse(null)
        return if (user == null) {
            ResponseEntity.notFound().build()
        }
        else {
            ResponseEntity.ok(user)
        }
    }
}
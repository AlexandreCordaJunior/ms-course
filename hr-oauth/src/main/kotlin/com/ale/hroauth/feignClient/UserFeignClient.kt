package com.ale.hroauth.feignClient

import com.ale.hroauth.domain.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@Component
@FeignClient(name = "hr-user", path = "/users")
interface UserFeignClient {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<User>

    @GetMapping("/search")
    fun getByEmail(@RequestParam email: String): ResponseEntity<User>
}
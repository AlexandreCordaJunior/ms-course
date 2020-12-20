package com.ale.hruser.service

import com.ale.hruser.domain.User
import com.ale.hruser.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(val repository: UserRepository) {

    fun getById(id: Long): Optional<User> {
        return repository.findById(id)
    }

    fun getByEmail(email: String): Optional<User> {
        return repository.findByEmail(email)
    }
}
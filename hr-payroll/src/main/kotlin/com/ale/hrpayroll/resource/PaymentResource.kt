package com.ale.hrpayroll.resource

import com.ale.hrpayroll.domain.Payment
import com.ale.hrpayroll.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentResource @Autowired constructor(private val service: PaymentService) {
    @GetMapping("/{workerId}/days/{days}")
    fun getPayment(@PathVariable workerId: Long, @PathVariable days: Int): ResponseEntity<Payment> {
        val payment = service.getPayment(workerId, days)
        return ResponseEntity.ok(payment)
    }
}
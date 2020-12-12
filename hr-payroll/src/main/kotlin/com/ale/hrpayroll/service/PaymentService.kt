package com.ale.hrpayroll.service

import com.ale.hrpayroll.domain.Payment
import org.springframework.stereotype.Service

@Service
class PaymentService {
    fun getPayment(workerId: Long, days: Int): Payment {
        return Payment("Bob", 200.0, days)
    }    
}
package com.ale.hrpayroll.service

import com.ale.hrpayroll.domain.Payment
import com.ale.hrpayroll.feignClient.WorkerFeignClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PaymentService @Autowired constructor(
    private val workerFeignClient: WorkerFeignClient
) {
    fun getPayment(workerId: Long, days: Int): Payment? {
        val worker = workerFeignClient.findById(workerId).body
        return if(worker != null) {
            Payment(worker.name, worker.dailyIncome, days)
        }
        else {
            null
        }
    }    
}
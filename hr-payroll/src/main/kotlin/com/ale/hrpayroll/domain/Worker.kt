package com.ale.hrpayroll.domain

import java.io.Serializable

data class Worker(
    var id: Long,
    var name: String,
    var dailyIncome: Double
): Serializable
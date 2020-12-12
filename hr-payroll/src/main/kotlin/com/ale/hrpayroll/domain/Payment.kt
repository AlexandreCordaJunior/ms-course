package com.ale.hrpayroll.domain

import java.io.Serializable

data class Payment(var name: String, var dailyIncome: Double, var days: Int): Serializable {
    val total: Double
        get() {
            return dailyIncome * days;
        }
}
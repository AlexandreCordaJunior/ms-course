package com.ale.hrpayroll.domain

data class Payment(var name: String, var dailyIncome: Double, var days: Int) {
    val total: Double
        get() {
            return dailyIncome * days;
        }
}
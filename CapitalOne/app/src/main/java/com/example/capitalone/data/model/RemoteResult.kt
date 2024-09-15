package com.example.capitalone.data.model

data class RemoteResult(
    val _id: String,
    val account_number: String,
    val balance: Int,
    val customer_id: String,
    val nickname: String,
    val rewards: Int,
    val type: String
)
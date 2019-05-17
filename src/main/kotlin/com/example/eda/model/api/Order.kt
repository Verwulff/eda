package com.example.eda.model.api

import com.example.eda.model.storage.OrderStatus

data class Order(
    val id: Long,
    val products: ArrayList<String> = arrayListOf(),
    var status: OrderStatus,
    val operatorId: Long? = null,
    val clientId: Long,
    var courierId: Long? = null
)
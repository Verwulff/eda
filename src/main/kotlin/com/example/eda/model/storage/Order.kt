package com.example.eda.model.storage

import javax.persistence.*
import com.example.eda.model.storage.Entity as DbEntity

@Entity
@Table(name = "orderrequest")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0,
    var status: OrderStatus = OrderStatus.WAIT_CONFIRM,

    @Basic
    var products: ArrayList<String> = ArrayList(),

    @ManyToOne @JoinColumn(name = "operator_id")
    val operator: Operator? = null,

    @ManyToOne @JoinColumn(name = "courier_id")
    var courier: Courier? = null,

    @ManyToOne @JoinColumn(name = "client_id")
    var client: Client
) : DbEntity


enum class OrderStatus {
    WAIT_CONFIRM, CONFIRMED, WAIT_DELIVERY, IN_DELIVERY, COMPLETE, CANCELED
}
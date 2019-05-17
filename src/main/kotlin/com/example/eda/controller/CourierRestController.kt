package com.example.eda.controller

import com.example.eda.model.api.LoginAndPassword
import com.example.eda.model.storage.Courier
import com.example.eda.repository.CourierRepository
import com.example.eda.util.extension.ElementAlreadyExists
import com.example.eda.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/couriers")
class CourierRestController(private val couriers: CourierRepository) {

    @GetMapping("/all")
    fun all() = couriers.findAll()

    @PostMapping("/create")
    fun create(@RequestBody courier: Courier): Any = when {
        couriers.findAll().any { it.login == courier.login } -> ElementAlreadyExists()
        else -> couriers.save(courier)
    }

    @PostMapping("/sign")
    fun signIn(@RequestBody loginAndPassword: LoginAndPassword): Any {
        val (login, password) = loginAndPassword
        return couriers.findAll().find { it.login == login && it.password == password } ?: ElementNotFound()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        couriers.findById(id).get()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        couriers.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }
}
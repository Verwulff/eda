package com.example.eda.controller

import com.example.eda.model.api.LoginAndPassword
import com.example.eda.model.storage.Operator
import com.example.eda.repository.OperatorRepository
import com.example.eda.util.extension.ElementAlreadyExists
import com.example.eda.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/operators")
class OperatorRestController(private val operators: OperatorRepository) {

    @GetMapping("/all")
    fun all() = operators.findAll()

    @PostMapping("/create")
    fun create(@RequestBody operator: Operator): Any = when {
        operators.findAll().any { it.login == operator.login } -> ElementAlreadyExists()
        else -> operators.save(operator)
    }

    @PostMapping("/sign")
    fun signIn(@RequestBody loginAndPassword: LoginAndPassword): Any {
        val (login, password) = loginAndPassword
        return operators.findAll().find { it.login == login && it.password == password } ?: ElementNotFound()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        operators.findById(id).get()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        operators.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }
}
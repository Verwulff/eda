package com.example.eda.controller

import com.example.eda.model.api.LoginAndPassword
import com.example.eda.model.storage.Client
import com.example.eda.repository.ClientRepository
import com.example.eda.util.extension.ElementAlreadyExists
import com.example.eda.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/clients")
class ClientRestController(private val clients: ClientRepository) {

    @GetMapping("/all")
    fun all(): Iterable<Client> = clients.findAll().toList()

    @PostMapping("/create")
    fun create(@RequestBody client: Client): Any = when {
        clients.findAll().any { it.login == client.login } -> ElementAlreadyExists()
        else -> clients.save(client)
    }

    @PostMapping("/sign")
    fun signIn(@RequestBody loginAndPassword: LoginAndPassword): Any {
        val (login, password) = loginAndPassword
        return clients.findAll().find { it.login == login && it.password == password } ?: ElementNotFound()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        clients.findById(id).get()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        clients.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }
}
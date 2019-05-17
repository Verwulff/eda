@file:Suppress("FunctionName")

package com.example.eda.util.extension

data class Error(val error: String)

fun ElementAlreadyExists() = Error("Element already exists")
fun ElementNotFound() = Error("Element not found")
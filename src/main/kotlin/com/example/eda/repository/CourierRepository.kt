package com.example.eda.repository

import com.example.eda.model.storage.Courier
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourierRepository : CrudRepository<Courier, Long>
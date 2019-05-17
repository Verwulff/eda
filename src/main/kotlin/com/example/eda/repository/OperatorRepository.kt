package com.example.eda.repository

import com.example.eda.model.storage.Operator
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OperatorRepository : CrudRepository<Operator, Long>
package com.example.expensetracker.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.expensetracker.data.Budget

@Dao
interface BudgetDao {

    @Query("SELECT * FROM Budget")
    fun getAllBudgets(): LiveData<List<Budget>>

    @Insert
    suspend fun insertBudget(budget: Budget)

    @Update
    suspend fun updateBudget(budget: Budget)

    @Query("SELECT * FROM Budget WHERE id = :id")
    suspend fun getBudgetById(id: Int): Budget?
}

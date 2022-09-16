package com.example.disneycodechallenge.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {

    @Query("SELECT * FROM customer_table ORDER BY name ASC")
    fun getAlphabetizedCustomers(): Flow<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    @Query("DELETE FROM customer_table")
    suspend fun deleteAll()
}
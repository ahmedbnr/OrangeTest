package com.example.examenprincipal4sim1

import androidx.room.*
import com.example.test.models.Quotes


@Dao
interface quotesDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insert(car: Quotes)

    @Update
    fun update(car: Quotes)

    @Delete
    fun delete(car: Quotes)

    @Query("SELECT * FROM quotes")
    fun getAllFavorites(): MutableList<Quotes>
}
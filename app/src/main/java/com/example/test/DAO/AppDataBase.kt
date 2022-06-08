package com.example.examenprincipal4sim1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test.models.Quotes
import com.example.test.models.Tinder


@Database(entities = [Quotes::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun quotesDao(): quotesDao


    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance =
                        Room.databaseBuilder(context, AppDataBase::class.java, "quotes")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return instance!!
        }
    }
}
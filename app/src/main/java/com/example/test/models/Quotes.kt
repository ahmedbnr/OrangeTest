package com.example.test.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


const val PICTURE = "PICTURE"
const val NAME = "NAME"

const val AUTHOR = "AUTHOR"
const val PRIX = "PRIX"

@Entity(tableName = "quotes")
data class Quotes(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "activity")
    val activity: String


    ) {

}

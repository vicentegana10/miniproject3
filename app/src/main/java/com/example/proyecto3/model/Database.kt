package com.example.proyecto3.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Gif::class],version = 1)
abstract class Database: RoomDatabase(){
    abstract fun gifDao(): GifDao
}
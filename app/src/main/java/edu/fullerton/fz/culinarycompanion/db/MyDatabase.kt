package edu.fullerton.fz.culinarycompanion.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [Favorite::class], version = 4
)
abstract class MyDatabase :RoomDatabase(){
    abstract fun myDao(): DataAccessObj
}
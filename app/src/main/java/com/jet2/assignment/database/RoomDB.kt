package com.jet2.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticlesEntity::class], version = 1)
abstract class ArticlesDatabase: RoomDatabase() {
    abstract val articlesDao: ArticlesDao
}

private lateinit var INSTANCE: ArticlesDatabase

fun getDatabase(context: Context): ArticlesDatabase {
    synchronized(ArticlesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ArticlesDatabase::class.java,
                "articles").build()
        }
    }
    return INSTANCE
}

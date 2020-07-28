package com.jet2.assignment.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticlesDao {
    @Query("select * from articlesentity")
    fun getArticles(): LiveData<List<ArticlesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( articles: List<ArticlesEntity>)
}

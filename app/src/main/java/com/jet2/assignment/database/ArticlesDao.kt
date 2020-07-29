package com.jet2.assignment.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ArticlesDao {
    @Query("select * from articlesentity")
    fun getArticles(): LiveData<List<ArticlesEntity>>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll( articles: List<ArticlesEntity>)


    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert( article: ArticlesEntity)
}

package com.jet2.assignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.jet2.assignment.database.ArticlesDatabase
import com.jet2.assignment.database.asModel
import com.jet2.assignment.model.Articles
import com.jet2.assignment.network.NetworkClient
import com.jet2.assignment.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticlesRepository(private val database: ArticlesDatabase) {

    val articles: LiveData<List<Articles>> = Transformations.map(database.articlesDao.getArticles()) {
        it.asModel()
    }

    /**
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     */
    suspend fun getArticles(pageNumber : Int, limit : Int ) {
        withContext(Dispatchers.IO) {
            val articles  =
                NetworkClient.articlesService.getArticles(pageNumber, limit).await()
            database.articlesDao.insertAll(articles.asDatabaseModel())
        }
    }

}
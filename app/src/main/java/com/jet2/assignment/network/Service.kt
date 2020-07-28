package com.jet2.assignment.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * A retrofit service to fetch a articles.
 */
interface ArticlesService {
    @GET("/jet2/api/v1/blogs")
    fun getArticles(@Query("page") pageNumber:Int, @Query("limit") limit:Int): Deferred<ArticlesList>
}

/**
 * Main entry point for network access. Call like `NetworkClient.articlesService.getArticles()`
 */
object NetworkClient {

    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://5e99a9b1bc561b0016af3540.mockapi.io/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val articlesService = retrofit.create(ArticlesService::class.java)
}


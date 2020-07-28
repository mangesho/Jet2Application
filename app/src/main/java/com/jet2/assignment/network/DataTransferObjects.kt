package com.jet2.assignment.network

import com.jet2.assignment.database.ArticlesEntity
import com.jet2.assignment.database.MediaEntity
import com.jet2.assignment.database.UserEntity
import com.squareup.moshi.JsonClass



/**
 * list of articles class
 */
@JsonClass(generateAdapter = true)
class ArticlesList : ArrayList<ArticleData>()

/**
 * article model class for network response
 */
@JsonClass(generateAdapter = true)
data class ArticleData(
    var id: String,
    var createdAt: String,
    var content: String,
    var comments: Int,
    var likes: Int,
    var media: List<MediaData> = mutableListOf(),
    var user: List<UserData> = mutableListOf())



/**
 * media model class for network response
 */
@JsonClass(generateAdapter = true)
data class MediaData(
    var id: String,
    var blogId: String,
    var createdAt: String,
    var imageurl: String,
    var title: String,
    var url: String)


/**
 * user model class for network response
 */
@JsonClass(generateAdapter = true)
data class UserData(
    var id: String,
    var blogId: String,
    var createdAt: String,
    var name: String,
    var avatar: String,
    var lastname: String,
    var city: String,
    var designation: String,
    var about: String
)



/**
 * Convert Network results to database objects
 */
fun ArticlesList.asDatabaseModel(): List<ArticlesEntity> {
    return map {
        ArticlesEntity(
            id = it.id,
            createdAt = it.createdAt,
            content = it.content,
            comments = it.comments,
            likes = it.likes,

            media = it.media.map { MediaEntity(id = it.id, blogId = it.blogId,
            createdAt = it.createdAt, imageurl = it.imageurl, title = it.imageurl, url = it.url) },

            user = it.user.map { UserEntity(id = it.id,blogId = it.blogId,createdAt = it.createdAt,
                name = it.name, avatar = it.avatar, lastname = it.lastname,
                city = it.city, designation = it.designation, about = it.about) }
        )
    }
}

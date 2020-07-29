package com.jet2.assignment.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jet2.assignment.model.Articles
import com.jet2.assignment.model.Media
import com.jet2.assignment.model.User


@Entity
data class ArticlesEntity constructor(
    @PrimaryKey
    var id: String,
    var createdAt: String,
    var content: String,
    var comments: Int,
    var likes: Int,
    var media: List<MediaEntity>,
    var user: List<UserEntity> )


data class MediaEntity(
    var id: String,
    var blogId: String,
    var createdAt: String,
    var imageurl: String,
    var title: String,
    var url: String)

data class UserEntity(
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
 * Map database model to user model
 */
fun List<ArticlesEntity>.asModel(): List<Articles> {
    return map {
        Articles(
            id = it.id,
            createdAt = it.createdAt,
            content = it.content,
            comments = it.comments,
            likes = it.likes,
            media = if(it.media.isNullOrEmpty()) null else it.media[0].toMedia(),
            user =  if(it.user.isNullOrEmpty()) null else it.user[0].toUser()
        )
    }
}

fun MediaEntity.toMedia() = Media(
    id = id,
    blogId = blogId,
    createdAt = createdAt,
    url = url,
    title = title,
    imageurl = imageurl
)

fun UserEntity.toUser() = User(
    id = id,
    blogId = blogId,
    about = about,
    designation = designation,
    city = city,
    lastname = lastname,
    avatar = avatar,
    name = name,
    createdAt = createdAt
)

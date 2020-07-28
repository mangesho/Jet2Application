package com.jet2.assignment.model

import com.jet2.assignment.utils.getFormattedDayValue
import com.jet2.assignment.utils.getFormattedNumber


/**
 * This is an plain Kotlin data classes that represent the things in our app. These are the
 * objects that should be displayed on screen, or manipulated by the app.
 *
 * @see database for objects that are mapped to the database
 * @see network for objects that parse or prepare network calls
 */

/**
 * articles represent a list of articles which displayed in list
 */
data class Articles(val id: String,
                 val createdAt: String,
                 val content: String,
                 val comments: Int,
                 val likes: Int,
                 val media: Media?,
                 val user: User?) {

    val displayDate: String
        get() = getFormattedDayValue(createdAt)

    val displayCommentCount : String
        get() = getFormattedNumber(comments, "Comments")

    val displayLikesCount : String
        get() = getFormattedNumber(likes, "Likes")

}


/**
 * media
 */
data class Media(
    val id: String,
    val blogId: String,
    val createdAt: String,
    val imageurl: String,
    val title: String,
    val url: String){

}


/**
 * user
 */
data class User(
    val id: String,
    val blogId: String,
    val createdAt: String,
    val name: String,
    val avatar: String,
    val lastname: String,
    val city: String,
    val designation: String,
    val about: String){

    val displyUserName : String
    get() = "$name $lastname"
}


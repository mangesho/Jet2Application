package com.jet2.assignment.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.jet2.assignment.R
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



    var displayDate: String = getFormattedDayValue(createdAt)
    var displayCommentCount : String = getFormattedNumber(comments, "Comments")
    var displayLikesCount : String = getFormattedNumber(likes, "Likes")

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

    companion object {
        // This will load the image using Glide in imageView of recycler items
        @BindingAdapter("articleImage")
        @JvmStatic
        fun loadArticleImage(view: ImageView, articleImage: String) {
            Glide.with(view.context)
                .load(articleImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)

        }
    }
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

    companion object {
        // This will load the image using Glide in imageView of recycler items
        @BindingAdapter("avatarImage")
        @JvmStatic
        fun loadAvatarImage(view: ImageView, avatarImage: String) {
            Glide.with(view.context)
                .load(avatarImage)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(view)
        }
    }
}


package com.jet2.assignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jet2.assignment.R
import com.jet2.assignment.databinding.ArticleItemBinding
import com.jet2.assignment.model.Articles


class ArticlesAdapter() : RecyclerView.Adapter<ArticleViewHolder>() {

    /**
     * The videos that our Adapter will show
     */
    var articles: List<Articles> = emptyList()
        set(value) {
            field = value

            // Notify any registered observers that the data set has changed. This will cause every
            // element in our RecyclerView to be invalidated.
            notifyDataSetChanged()
        }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val withDataBinding: ArticleItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArticleViewHolder.LAYOUT,
            parent,
            false)
        return ArticleViewHolder(withDataBinding)
    }

    override fun getItemCount() = articles.size

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     */
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.articleModel = articles[position]
        }
    }

}

/**
 * ViewHolder for items. All work is done by data binding.
 */
class ArticleViewHolder(val viewDataBinding: ArticleItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.article_item
    }
}
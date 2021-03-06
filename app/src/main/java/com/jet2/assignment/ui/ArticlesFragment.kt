package com.jet2.assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jet2.assignment.R
import com.jet2.assignment.databinding.FragmentArticlesBinding
import com.jet2.assignment.model.Articles
import com.jet2.assignment.viewmodels.ArticleViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ArticlesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticlesFragment : Fragment() {

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onActivityCreated, which we
     * do in this Fragment.
     */
    private val viewModel: ArticleViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProviders.of(this, ArticleViewModel.Factory(activity.application))
            .get(ArticleViewModel::class.java)
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */
    private var articlesAdapter: ArticlesAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.articles.observe(viewLifecycleOwner, Observer<List<Articles>> { articles ->
            articles?.apply {
                articlesAdapter?.articles = articles
                viewModel.hideProgress()
            }
        })
    }

    var isLoading: Boolean = false
    var hasMoreItem: Boolean = true
    var pageNumber: Int = 1
    private val PAGES_LIMIT = 10

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentArticlesBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_articles,
            container,
            false)
        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        articlesAdapter = ArticlesAdapter()

        binding.root.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = articlesAdapter

            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    val itemCount = this@apply.layoutManager?.itemCount ?: 0
                    val lastVisibleItem =
                        (this@apply.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    if (!isLoading && hasMoreItem
                        && itemCount == lastVisibleItem + 1
                    ) {
                        pageNumber += 1
                        viewModel.getData(pageNumber, PAGES_LIMIT)
                    } else if (!hasMoreItem && itemCount == lastVisibleItem + 1)
                        viewModel.hideProgress()

                }
            })


        }

        // Observer for the network error.
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean> { isLoading ->
            this.isLoading = isLoading
        })

        viewModel.hasMoreItems.observe(viewLifecycleOwner, Observer<Boolean> { hasMoreItmes ->
            this.hasMoreItem = hasMoreItmes
        })

        return binding.root
    }

    private fun onNetworkError() {
        Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
        viewModel.hideProgress()
    }

}
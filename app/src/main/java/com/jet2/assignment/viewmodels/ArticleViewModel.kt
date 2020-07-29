package com.jet2.assignment.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.jet2.assignment.database.getDatabase
import com.jet2.assignment.repository.ArticlesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private val PAGE_LIMIT = 10

    /**
     * The data source this ViewModel will fetch results from.
     */
    private val repository = ArticlesRepository(getDatabase(application))

    /**
     * A article list displayed on the screen.
     */
    val articles = repository.articles

    /**
     * This is the job for all coroutines started by this ViewModel.
     *
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     *
     * Since we pass viewModelJob, you can cancel all coroutines launched by uiScope by calling
     * viewModelJob.cancel()
     */
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isShowProgress = MutableLiveData<Boolean>(false)

    val isShowProgress: LiveData<Boolean>
        get() = _isShowProgress


    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        getData()
    }

    /**
     * get data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    private fun getData() {
        viewModelScope.launch {
            try {
                repository.getArticles(1, PAGE_LIMIT )
                _eventNetworkError.value = false
                _isShowProgress.value = true

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(articles.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    /**
     * Resets the network error flag.
     */
    fun hideProgress() {
        _isShowProgress.value = false
    }


    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArticleViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
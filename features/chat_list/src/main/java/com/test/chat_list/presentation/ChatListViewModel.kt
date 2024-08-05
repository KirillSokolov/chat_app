package com.test.chat_list.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.chat_list.domain.AddChatUseCase
import com.test.chat_list.domain.GetAllChatUseCase
import kotlinx.coroutines.launch

class ChatListViewModel (private val addChatUseCase: AddChatUseCase, private val getAllChatUseCase: GetAllChatUseCase) :
    ViewModel() {

    private val _news = MutableLiveData<List<Article>>(emptyList())
    val news: LiveData<List<Article>> = _news

    private val _screen = MutableLiveData<NewsScreen>()
    val screen: LiveData<NewsScreen> = _screen

    fun load() {
        viewModelScope.launch {
            val articles = getArticlesUseCase.execute()
            _news.postValue(articles)
        }
    }

    fun articleClicked(article: Article) {
        val articleDetails = ArticleDetailsArg(
            title = article.title,
            summary = article.description,
            imageUrl = article.imageUrl,
            articleUrl = article.articleUrl
        )
        _screen.value = NewsScreen.Details(article = articleDetails)
        _screen.value = NewsScreen.Nothing
    }
}

class ChatListViewModelFactory(private val addChatUseCase: AddChatUseCase, private val getAllChatUseCase: GetAllChatUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return ChatListViewModel(
            addChatUseCase = addChatUseCase,
            getAllChatUseCase = getAllChatUseCase
        ) as T
    }
}
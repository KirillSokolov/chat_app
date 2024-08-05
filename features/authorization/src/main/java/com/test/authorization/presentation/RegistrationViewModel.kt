package com.test.authorization.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.test.authorization.domain.RegistrationUseCase
import kotlinx.coroutines.launch

class RegistrationViewModel(private val useCase: RegistrationUseCase) :
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

class RegistrationViewModelFactory(private val registrationUseCase: RegistrationUseCase) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>,
        extras: CreationExtras,
    ): T {
        return RegistrationViewModel(
            useCase = registrationUseCase
        ) as T
    }
}
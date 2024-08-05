package com.test.chat.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiparo.newsappfeaturedagger.news.R
import com.test.chat.di.ChatFeatureDepsProvider
import com.test.navigation.Router
import javax.inject.Inject

class ChatFragment : Fragment(R.layout.fragment_news) {

    @Inject
    lateinit var vmFactory: ChatViewModelFactory

    @Inject
    lateinit var router: Router


    private val viewModel by viewModels<ChatViewModel> {
        vmFactory
    }

    private val adapter = NewsAdapter {
        viewModel.articleClicked(article = it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authorizationComponent = DaggerNewsComponent
            .builder()
            .addDeps(ChatFeatureDepsProvider.deps)
            .build()

        authorizationComponent.inject(this)

        if (savedInstanceState == null) {
            viewModel.load()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentNewsBinding.bind(view)

        binding.toolbar.inflateMenu(R.menu.menu_news)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> {
                    true
                }

                else -> false
            }
        }

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.newsRecyclerView.addItemDecoration(
            SpaceDecoration(
                spaceSize = resources.getDimensionPixelSize(
                    com.kiparo.newsappfeaturedagger.core.ui.R.dimen.padding_10
                )
            )
        )
        binding.newsRecyclerView.adapter = adapter

        viewModel.news.observe(viewLifecycleOwner) {
            adapter.items = it
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            if (it is NewsScreen.Details)
                showArticleDetails(articleDetails = it.article)
        }
    }

    private fun showArticleDetails(articleDetails: ArticleDetailsArg) {
        router.navigateTo(
            fragment = articleDetailsFeatureApi.open(articleDetails),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): ChatFragment = ChatFragment()
    }
}
package com.test.chat.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.chat.di.ChatFeatureDepsProvider
import com.test.chat.di.DaggerChatComponent
import com.test.chat_list_api.ChatListFeatureApi
import com.test.chatapp.news.R
import com.test.chatapp.news.databinding.FragmentChatBinding
import com.test.navigation.Router
import com.test.ui.SpaceDecoration
import javax.inject.Inject

class ChatFragment : Fragment(R.layout.fragment_chat) {

    @Inject
    lateinit var vmFactory: ChatViewModelFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var chatListFeatureApi: ChatListFeatureApi

    private val viewModel by viewModels<ChatViewModel> {
        vmFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chatComponent = DaggerChatComponent
            .builder()
            .addDeps(ChatFeatureDepsProvider.deps)
            .build()

        chatComponent.inject(this)

        if (savedInstanceState == null) {
            viewModel.load()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentChatBinding.bind(view)

        binding.btnBack.setOnClickListener{
            viewModel.backButtonClicked()
        }

        binding.recycleView.layoutManager = LinearLayoutManager(context)
        binding.recycleView.addItemDecoration(
            SpaceDecoration(
                spaceSize = resources.getDimensionPixelSize(
                  com.test.chatapp.core.ui.R.dimen.padding_10
                )
            )
        )

        viewModel.messages.observe(viewLifecycleOwner) {
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            if (it is NextScreen.ChatListFragment)
                showChatListFragment()
        }
    }

    private fun showChatListFragment() {
        router.navigateTo(
            fragment = chatListFeatureApi.open(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): ChatFragment = ChatFragment()
    }
}
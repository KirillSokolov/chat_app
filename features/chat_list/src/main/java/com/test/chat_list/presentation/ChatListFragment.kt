package com.test.chat_list.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.chat_api.ChatFeatureApi
import com.test.chat_list.di.ChatListFeatureDepsProvider
import com.test.chat_list.di.DaggerChatListComponent
import com.test.chatapp.chat.list.R
import com.test.chatapp.chat.list.databinding.FragmentChatListBinding
import com.test.navigation.Router
import com.test.ui.ChatAppAdapter
import com.test.ui.SpaceDecoration
import com.test.user_details_api.UserDetailsFeatureApi
import javax.inject.Inject

class ChatListFragment : Fragment(R.layout.fragment_chat_list) {

    @Inject
    lateinit var vmFactory: ChatListViewModelFactory

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var chatFeatureApi: ChatFeatureApi

    @Inject
    lateinit var userDetailsFeatureApi: UserDetailsFeatureApi

    private val viewModel by viewModels<ChatListViewModel> {
        vmFactory
    }

    private val adapter = ChatAppAdapter {
        viewModel.chatClicked(it.id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val chatListComponent = DaggerChatListComponent
            .builder()
            .addDeps(ChatListFeatureDepsProvider.deps)
            .build()

        chatListComponent.inject(this)

        if (savedInstanceState == null) {
            viewModel.load()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentChatListBinding.bind(view)

        binding.btnUser.setOnClickListener{
            viewModel.userClicked()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            SpaceDecoration(
                spaceSize = resources.getDimensionPixelSize(
                    com.test.chatapp.core.ui.R.dimen.padding_10
                )
            )
        )
        binding.recyclerView.adapter = adapter

        viewModel.chats.observe(viewLifecycleOwner) {
            adapter.items = it
        }

        viewModel.screen.observe(viewLifecycleOwner) {
            when(it){
                is NextScreen.ChatFragment -> {
                    showChatFragment()
                }
                is NextScreen.UserFragment -> {
                    showUserFragment()
                }
                NextScreen.Nothing -> {}
            }
        }
    }

    private fun showUserFragment() {
        router.navigateTo(
            fragment = userDetailsFeatureApi.open(),
            addToBackStack = true
        )
    }

    private fun showChatFragment() {
        router.navigateTo(
            fragment = chatFeatureApi.open(),
            addToBackStack = true
        )
    }

    companion object {
        fun getInstance(): ChatListFragment = ChatListFragment()
    }
}
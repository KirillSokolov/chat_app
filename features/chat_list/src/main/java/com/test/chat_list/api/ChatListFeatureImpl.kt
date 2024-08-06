package com.test.chat_list.api

import androidx.fragment.app.Fragment
import com.test.chat_list.presentation.ChatListFragment
import com.test.chat_list_api.ChatListFeatureApi
import javax.inject.Inject

class ChatListFeatureImpl @Inject constructor(): ChatListFeatureApi {
    override fun open(): Fragment {
        return ChatListFragment.getInstance()
    }
}
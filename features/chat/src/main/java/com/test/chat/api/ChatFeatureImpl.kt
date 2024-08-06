package com.test.chat.api

import androidx.fragment.app.Fragment
import com.test.chat.presentation.ChatFragment
import com.test.chat_api.ChatFeatureApi
import javax.inject.Inject

class ChatFeatureImpl @Inject constructor(): ChatFeatureApi {
    override fun open(): Fragment {
        return ChatFragment.getInstance()
    }
}
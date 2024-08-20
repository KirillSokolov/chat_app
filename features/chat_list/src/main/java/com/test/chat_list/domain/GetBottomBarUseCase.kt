package com.test.chat_list.domain

import com.test.navigation.ChatTopLevelDestination
import com.test.ui.BottomState

class GetBottomBarUseCase {

    suspend fun execute():List<ChatTopLevelDestination> {
        BottomState.addChatList()
        BottomState.addProfile()
        return BottomState.chatMenu
    }
}
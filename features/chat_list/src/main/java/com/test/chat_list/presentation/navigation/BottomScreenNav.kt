package com.test.chat_list.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.test.chat_list.di.ChatListFeatureDepsProvider
import com.test.chat_list.di.DaggerChatListComponent
import com.test.chat_list.presentation.ui.ChatListScreen
import com.test.chat_list.presentation.ui.ChatScreen
import com.test.chat_list.presentation.viewmodel.ChatListViewModel
import com.test.chat_list.presentation.viewmodel.ChatViewModel
import com.test.navigation.AboutDestination
import com.test.navigation.BOTTOM_MENU_ROUTE
import com.test.navigation.BottomMenuNavigator
import com.test.navigation.ChatDestination
import com.test.navigation.ChatListDestination
import com.test.user.details.presentation.navigation.*

fun NavGraphBuilder.bottom_menu(externalNavigator: BottomMenuNavigator) {
    navigation(startDestination = ChatListDestination.route, route = BOTTOM_MENU_ROUTE) {
        chatList(
            onNavigateChat = externalNavigator::onChat,
            onNavigateDetails = externalNavigator::onNavigateToDetails,
        )
        chat (
            onBack = externalNavigator::onChatList
        )
        details(
            onNavigate = externalNavigator::onNavigateToEdit,
            onBack = externalNavigator::onChatList,
        )
        editDetails(  onNavigate = externalNavigator::onNavigateToAbout,
            onBack = externalNavigator::onNavigateToDetails,)
        aboutUser(onBack = externalNavigator::onNavigateToEdit)
    }
}





private fun NavGraphBuilder.chat(
    onBack: () -> Unit
) {
    composable(route = ChatDestination.route) {
        val component = DaggerChatListComponent.builder().addDeps(
            ChatListFeatureDepsProvider.deps).build()
        val viewModel:ChatViewModel = viewModel()

        val uiState by viewModel.chats.collectAsStateWithLifecycle()

        ChatScreen(
            chatState = uiState,
            viewModel = viewModel
        )

    }
}

private fun NavGraphBuilder.chatList(
    onNavigateChat: () -> Unit,  onNavigateDetails: () -> Unit
) {
    composable(route = ChatListDestination.route) {
        val component = DaggerChatListComponent.builder().addDeps(
            ChatListFeatureDepsProvider.deps).build()

        val viewModel:ChatListViewModel = viewModel(factory = component.getChatListViewModelFactory())
        viewModel.load()

        viewModel.chats.value?.let { chats ->
            ChatListScreen(
                chatState = chats,
                onChatClick = onNavigateChat,
                onDetailsClick = onNavigateDetails
            )
        }
    }
}

@Composable
fun Toast(message: String) {
    val context = LocalContext.current

    android.widget.Toast.makeText(context, message, android.widget.Toast.LENGTH_SHORT).show()
}

@Composable
fun Toast(resId: Int) {
    val context = LocalContext.current

    android.widget.Toast.makeText(context, context.getText(resId), android.widget.Toast.LENGTH_SHORT).show()
}
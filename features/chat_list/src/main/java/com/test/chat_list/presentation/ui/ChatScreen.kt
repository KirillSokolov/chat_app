package com.test.chat_list.presentation.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.test.chat_list.presentation.viewmodel.ChatViewModel
import com.test.chat_list.presentation.viewmodel.MessageUiState
import com.test.ui.design.theme.padding16
import com.test.ui.design.theme.padding55
import com.test.ui.widgets.MessageBubble

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ChatScreen(
    chatState: MessageUiState,
    viewModel: ChatViewModel
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (messages, chatBox) = createRefs()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth().constrainAs(messages) {
                    top.linkTo(parent.top)
                    bottom.linkTo(chatBox.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                },
            reverseLayout = true,
        ) {
            //  val groupByTimestampHistoryList = chatState.data.groupBy { it.formattedDate }

            //chatState.forEach {  messages ->
            items(chatState.messages.size) { index ->
                val isSender = true
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = if (isSender)
                        Alignment.CenterEnd
                    else
                        Alignment.CenterStart
                ) {
                    MessageBubble(
                        message = chatState.messages[index],
                        isSender = true,
                        "",
                        ""
                    )
                }
            }
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Today",
                        style = MaterialTheme.typography.bodySmall
                            .copy(color = MaterialTheme.colorScheme.onBackground)
                    )
                }
            }
        }


        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(chatBox) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.padding(bottom = padding55, start = padding16, end = padding16),
            border = BorderStroke(
                width = 0.5.dp,
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f)
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                disabledContentColor = MaterialTheme.colorScheme.background
            )
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.messageText.value,
                onValueChange = viewModel::onMessageChange,
                placeholder = {
                    Text(
                        text = "Type message...",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                    )
                },
                singleLine = false,
                maxLines = 4,
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = MaterialTheme.colorScheme.background,
                    focusedIndicatorColor = MaterialTheme.colorScheme.background,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                    disabledIndicatorColor = MaterialTheme.colorScheme.background,
                    containerColor = MaterialTheme.colorScheme.background
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable { viewModel.sendMessage() }
                            .rotate(-45f),
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send message",
                        tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                    )
                }
            )
        }
    }
}
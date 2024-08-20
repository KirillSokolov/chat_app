package com.test.chat_list.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.test.domain.models.chat.Chat
import com.test.ui.design.theme.itemHeight75
import com.test.ui.design.theme.itemWidth104
import com.test.ui.design.theme.space16
import com.test.ui.design.theme.space24
import com.test.ui.design.theme.space32
import com.test.ui.design.theme.space8
import com.test.ui.widgets.BackgroundColumn
import com.test.ui.widgets.CartItem
import com.test.ui.widgets.DefaultButton
import com.test.ui.widgets.StatusBarInsetsSpacer
import com.test.ui.widgets.IconHeader

@Composable
fun ChatListScreen(
    chatState: List<Chat>,
    onChatClick: () -> Unit,
    onDetailsClick: () -> Unit)
{
    BackgroundColumn {
        StatusBarInsetsSpacer()

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.clickable(
                    onClick = onDetailsClick,
                ),
                painter = painterResource(com.test.chatapp.core.ui.R.drawable.ic_profile),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(space16))
        IconHeader(
            modifier = Modifier
                .height(itemHeight75)
                .width(itemWidth104)
        )

        Spacer(modifier = Modifier.height(space32))


        Text(
            text = stringResource(com.test.chatapp.core.ui.R.string.chat_list),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )

        Spacer(modifier = Modifier.height(space32))

        Column()
        {
            chatState.forEach{ item: Chat ->
                Column(modifier = Modifier) {

                CartItem(
                    id = item.id.toString(),
                    title = item.name,
                    count =  item.membersCount,
                    onDelete = {},
                    onClick = onChatClick
                )


                    Spacer(modifier = Modifier.height(space8))
                   // Text(text = item.membersCount)
                  //  Icon(imageVector = com.test.chatapp.core.ui.R.drawable.ic_profile, "")
                }

            }

        }

        Spacer(modifier = Modifier.height(space32))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = com.test.chatapp.core.ui.R.string.btn_add_chat,
            onClick = {}
        )
        Spacer(modifier = Modifier.height(space24))

    }



}
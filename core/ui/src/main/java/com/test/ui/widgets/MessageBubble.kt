package com.test.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.test.domain.models.chat.Message
val ENDPOINT_AVATAR = "https://avatars.dicebear.com/api/avataaars/"

@Composable
fun MessageBubble(
    message: Message,
    isSender: Boolean,
    senderAvatar: String,
    receiverAvatar: String
) {

    val radius =
        if (isSender) RoundedCornerShape(
            topStart = 16.dp,
            bottomStart = 16.dp,
            topEnd = 0.dp,
            bottomEnd = 16.dp
        ) else RoundedCornerShape(
            topStart = 0.dp,
            bottomStart = 16.dp,
            topEnd = 16.dp,
            bottomEnd = 16.dp
        )

    Row(
        modifier = Modifier
            .padding(bottom = 24.dp)
    ) {

        if (isSender.not()) {
            AvatarHead(message, senderAvatar, receiverAvatar, isSender)
        }

        Text(
            modifier = Modifier
                .padding(top = 8.dp)
                .weight(0.8f)
                .wrapContentSize(align = if (isSender) Alignment.CenterEnd else Alignment.CenterStart)
                .background(
                    color = if (isSender)
                        MaterialTheme.colorScheme.secondaryContainer
                    else
                        MaterialTheme.colorScheme.primaryContainer,
                    shape = radius
                )
                .padding(12.dp),
            text = message.text,
            color = if (isSender)
                MaterialTheme.colorScheme.onSecondaryContainer
            else
                MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Normal
        )

        if (isSender) {
            AvatarHead(message, senderAvatar, receiverAvatar, true)
        }

    }
}

@Composable
fun AvatarHead(
    message: Message,
    senderAvatar: String,
    receiverAvatar: String,
    isSender: Boolean
) {
    val avatar = "$ENDPOINT_AVATAR$senderAvatar.png"
    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(35.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = MaterialTheme.colorScheme.onBackground),
            painter = rememberAsyncImagePainter(model = avatar),
            contentDescription = "Friend avatar"
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = message.sendTime.toString(),
            color = Color.White,
            style = MaterialTheme.typography.bodySmall
                .copy(
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
        )
    }
}
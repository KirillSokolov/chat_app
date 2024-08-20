package com.test.user.details.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.space24
import com.test.ui.design.theme.space56
import com.test.ui.design.theme.space8
import com.test.ui.widgets.BackgroundColumn
import com.test.ui.widgets.FormField
import com.test.user.details.presentation.viewmodel.UserUIState

data class ImageWithText(
    val image: Painter,
    val text: String
)

@Composable
fun DetailsScreen(
    uiState: UserUIState,
    onNameChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onAboutChange: (String) -> Unit,
    onPhotoPick: ()->Unit) {
    BackgroundColumn {
        Spacer(modifier = Modifier.height(space24))

        ProfileSection(uiState, onPhotoPick)

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.clickable(
                    onClick = {  },
                ),
                painter = painterResource(com.test.chatapp.core.ui.R.drawable.ic_edit),
                contentDescription = ""
            )
        }
        Spacer(modifier = Modifier.height(space8))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.name,
            placeholderResId = com.test.chatapp.core.ui.R.string.user_name,
            leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_person,
            onValueChange = onNameChange
        )
        Spacer(modifier = Modifier.height(space24))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.nickName,
            placeholderResId = com.test.chatapp.core.ui.R.string.user_nickname,
            leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_person,
            onValueChange = onNicknameChange
        )
        Spacer(modifier = Modifier.height(space24))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.aboutMe,
            placeholderResId = com.test.chatapp.core.ui.R.string.user_about_yourself,
            leadingIconResId = R.drawable.ic_person,
            onValueChange = onAboutChange
        )

        Spacer(modifier = Modifier.height(space24))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.phone,
            enabled = false,
            placeholderResId = com.test.chatapp.core.ui.R.string.name_placeholder,
            leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_phone,
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(space56))

    }
}

@Composable
fun ProfileSection(uiState: UserUIState, onPhotoPick: ()->Unit ){
    Column(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            uiState.bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .weight(3f).clickable(onClick = onPhotoPick)
                        .border(2.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            } ?: RoundImage(
                image = painterResource(id = com.test.chatapp.core.ui.R.drawable.chat_main),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f).clickable(onClick = onPhotoPick) ,
                )

            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f))
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
){
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StatSection(modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(numberText = "0", text = "Posts")
        ProfileStat(numberText = "346", text = "Followers")
        ProfileStat(numberText = "368", text = "Following")
    }
}

@Composable
fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun HighlightSection(
    modifier: Modifier = Modifier,
    highlights: List<ImageWithText>
){
    LazyRow(modifier = modifier){
        items(highlights.size){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(end = 15.dp)
            ) {
                RoundImage(
                    image = highlights[it].image,
                    modifier = Modifier.size(70.dp)
                )
                Text(
                    text = highlights[it].text,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}
package com.test.ui.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.*


@Composable
fun EnterImage(modifier: Modifier = Modifier, @DrawableRes imageResId: Int) {
    Box(
        modifier = modifier
            .width(circleItemDiameter)
            .height(circleItemDiameter)
            .fillMaxSize()
            .aspectRatio(1f)
            .background(MaterialTheme.colorScheme.background, shape = CircleShape),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = Modifier
                .height(circleImageItemHeight)
                .width(circleImageItemWidth)
                .padding(top = padding34),
            painter = painterResource(id = imageResId),
            contentDescription = stringResource(R.string.image_description),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun EnterImagePreview() {
    ChatAppTheme {
      //  EnterImage(imageResId = R.drawable.huge_pizza)
    }
}
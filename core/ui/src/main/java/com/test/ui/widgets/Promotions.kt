package com.test.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.ui.design.theme.*
import com.test.chatapp.core.ui.R
import com.test.domain.models.Promotion

@Composable
fun Promotions(
    modifier: Modifier = Modifier,
    promotion: Promotion,
    label: String? = null
) {

    Box(modifier = modifier) {
        Column {
            label?.let {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(5.dp, RoundedCornerShape(8.dp))
                    .height(145.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(size = cornerRadius16)
                    )
                    .padding(padding16)

            ) {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = promotion.title,
                        style = bodyPromo, color = MaterialTheme.colorScheme.inversePrimary
                    )
                    val highlightSpan = SpanStyle(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
                        color = MaterialTheme.colorScheme.inversePrimary,
                    )
                    TextWithSingleLink(
                        sentence = promotion.offerPromo,
                        linkEntrySpanStyle = highlightSpan,
                    )
                    Spacer(modifier = Modifier.height(space40))
                    TextWithSingleLink(
                        sentence = promotion.offerPromoPrice,
                        linkEntrySpanStyle = highlightSpan
                    )
                }
            }
        }
        Image(
            modifier = Modifier
                .height(140.dp)
                .width(140.dp)
                .align(Alignment.TopEnd),
            painter = painterResource(id = R.drawable.french_fries),
            contentDescription = "Promotion image",
            contentScale = ContentScale.Fit
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PromotionsPreview() {
    ChatAppTheme {
        Promotions(
            promotion = Promotion(
                title = stringResource(id = R.string.promotions_title),
                offerPromo = stringResource(id = R.string.free_box_of_fries),
                offerPromoPrice = stringResource(id = R.string.on_all_orders_above, 150),
                promoCode = ""
            ),
            label = stringResource(id = R.string.promotions_title)
        )
    }
}
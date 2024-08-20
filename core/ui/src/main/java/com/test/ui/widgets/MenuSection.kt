package com.test.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import coil.compose.AsyncImage
import com.test.ui.MenuSection
import com.test.ui.design.theme.*
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.launch

@Composable
fun MenuCategorySection(
    id: String,
    title: String,
    imageUrl: String,
    contentDescription: String,
    selected: Boolean = false,
    onClick: (String) -> Unit
) {
    val color =
        if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            onClick(id)
        }) {
        Box(
            modifier = Modifier
                .width(itemWidth64)
                .height(itemHeight64)
                .background(
                    shape = RoundedCornerShape(cornerRadius16),
                    color = color
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(itemWidth48)
                    .height(itemHeight48)
                    .background(color = Color.Transparent)
                    .align(Alignment.Center),
                model = imageUrl, contentDescription = contentDescription,
            )
        }
        Text(
            modifier = Modifier.padding(top = padding8),
            text = title, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}


/*Previews*/
class ParamsProvider : PreviewParameterProvider<Pair<String, Boolean>> {
    override val values: Sequence<Pair<String, Boolean>> = sequenceOf(
        "All" to true,
        "Some" to false
    )
}


@Composable
fun MenuSections(
    modifier: Modifier = Modifier,
    sections: ImmutableList<MenuSection>,
    selected: Int,
    onSectionSelect: (String) -> Unit
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    //TODO: check the recomposition impact
    SideEffect {
        coroutineScope.launch {
            listState.animateScrollToItem(index = selected)
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(space8),
        contentPadding = PaddingValues(start = padding16, end = padding16)
    ) {
        itemsIndexed(sections) { index, section ->
            MenuCategorySection(
                id = section.section,
                title = section.section,
                imageUrl = section.image,
                contentDescription = section.section,
                selected = index == selected,
                onClick = { id ->
                    onSectionSelect(id)
                })
        }
    }
}
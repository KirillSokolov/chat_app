package com.test.chat_list.presentation.ui


import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.chat_list.presentation.viewmodel.BottomMenuUiState
import com.test.navigation.ChatTopLevelDestinationWithCount
import com.test.ui.design.theme.ChatAppTheme
import com.test.ui.widgets.AnimatedIconTab
import com.test.ui.widgets.ChatNavBar

@Composable
fun ChatBottomBar(
    modifier: Modifier = Modifier,
    bottomUiState: BottomMenuUiState,
    currentDestination: String?,
    onNavigateToTopLevel: (topRoute: String) -> Unit
) {
    ChatNavBar {
        bottomUiState.itemList.forEachIndexed { index, item ->
            AnimatedIconTab(
                modifier = modifier
                    .weight(1f)
                    .fillMaxHeight(),
                iconResourceId = item.iconId,
                contentDescription = stringResource(id = item.titleId),
                badgeIndicator = if (item is ChatTopLevelDestinationWithCount && item.badgeValue > 0)
                    item.badgeValue else null,
                selected = currentDestination == item.graph,
                onClicked = {  onNavigateToTopLevel(item.graph)  },
                selectedColor = MaterialTheme.colorScheme.primary,
                notSelectedColor = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@Preview
@Composable
fun ChatNavBarPreview() {
    ChatAppTheme {

    }
}
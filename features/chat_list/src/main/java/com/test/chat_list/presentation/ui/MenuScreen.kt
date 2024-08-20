package com.test.chat_list.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.test.chat_list.presentation.viewmodel.MenuUiState
import com.test.chatapp.chat.list.R
import com.test.ui.MenuItem
import com.test.ui.design.theme.*
import com.test.ui.widgets.LoadingIndicator
import com.test.ui.widgets.MenuSections
import com.test.ui.widgets.Promotions
import com.test.ui.widgets.ScreenTitle

@Composable
fun MenuScreen(
    uiState: MenuUiState,
    onSectionSelect: (String) -> Unit,
    onPromotionClick: () -> Unit,
    onItemSelected: (itemId: String) -> Unit,
    onAddToCartClicked: (itemId: MenuItem) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space16)
    ) {
        Column {
            ScreenTitle(
                textResId = com.test.chatapp.core.ui.R.string.menu,
                modifier = Modifier.padding(start = padding16)
            )
            Spacer(modifier = Modifier.height(space16))
            when (uiState) {
                is MenuUiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }

                is MenuUiState.Success -> {
                    ScreenContent(
                        uiState,
                        onSectionSelect = onSectionSelect,
                        onPromotionClick = onPromotionClick,
                        onItemSelected = onItemSelected,
                        onAddToCartClicked = onAddToCartClicked
                    )
                }

            }
        }
    }
}

@Composable
private fun ColumnScope.ScreenContent(
    uiState: MenuUiState.Success,
    onSectionSelect: (String) -> Unit,
    onPromotionClick: () -> Unit,
    onItemSelected: (String) -> Unit,
    onAddToCartClicked: (MenuItem) -> Unit
) {
    MenuSections(
        sections = uiState.sections,
        selected = uiState.selectedSectionIndex,
        onSectionSelect = onSectionSelect
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(padding8)
    ) {

      //  spanned {
//            Box(
//                modifier = Modifier
//                    .padding(padding8)
//                    .clickable {
//                        onPromotionClick()
//                    }
//            ) {
//                Promotions(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    promotion = uiState.promotion
//                )
//            }
        //}
    //    spanned {
           item {
               Box(
                modifier = Modifier
                    .padding(horizontal = padding8)
            ) {
                Text(
                    text = uiState.selectionTitle(),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
//        items(uiState.products) { menuItem ->
//            Box(
//                modifier =
//                Modifier
//                    .fillMaxSize()
//                    .padding(padding8)
//            ) {
//                ProductItem(
//                    item = menuItem,
//                    onAddToCartClicked = onAddToCartClicked,
//                    onItemClicked = onItemSelected
//                )
//            }
//        }
    }
}
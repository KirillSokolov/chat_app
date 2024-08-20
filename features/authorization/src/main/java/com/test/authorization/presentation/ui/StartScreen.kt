package com.test.authorization.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.test.authorization.di.AuthorizationComponent
import com.test.authorization.di.AuthorizationFeatureDepsProvider
import com.test.authorization.di.DaggerAuthorizationComponent
import com.test.data.temp.UserData
import com.test.ui.design.SystemBarsColorDisposableEffect
import com.test.ui.design.theme.*
import com.test.navigation.StartDestination
import com.test.navigation.StartNavigator
import com.test.ui.widgets.*

fun NavGraphBuilder.start(externalNavigator: StartNavigator){

    composable(route = StartDestination.route){
        val component = DaggerAuthorizationComponent.builder().addDeps(
            AuthorizationFeatureDepsProvider.deps).build()
        val showButton = remember { mutableStateOf(false) }
        val showHome = remember { mutableStateOf(false) }

        if (showHome.value)
            externalNavigator.onNavigateHome()

        LaunchedEffect(key1 = Any()) {
            val token = component.getDataPreference().getRefreshToken()
            if (token.isNotEmpty()) {
                showHome.value = true
            }else{
                showButton.value = true
            }
        }

        StartScreen(
            onGetStarted = externalNavigator::onNavigateAfterStarted,
            modifier = Modifier,
            showButton = showButton
        )
    }
}


@Composable
fun StartScreen(modifier: Modifier = Modifier, showButton: MutableState<Boolean>, onGetStarted: () -> Unit) {
    SystemBarsColorDisposableEffect(false)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(padding16)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatusBarInsetsSpacer()
        Spacer(modifier = Modifier.height(space16))
        SweetBite(
            modifier = Modifier
                .height(itemHeight75)
                .width(itemWidth104)
        )
        LogoTitle()
        Spacer(modifier = Modifier.height(space32))
        EnterImage(imageResId = com.test.chatapp.core.ui.R.drawable.huge_pizza)
        Spacer(modifier = Modifier.height(space32))
        LogoSlogan()
        Spacer(modifier = modifier.height(space40))

        if(showButton.value) {
            DefaultButton(
                modifier = Modifier.width(itemWidth216),
                textResId = com.test.chatapp.core.ui.R.string.get_started,
                onClick = onGetStarted
            )
            BottomBarInsetsSpacer()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    ChatAppTheme {
    }
}
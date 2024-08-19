package com.test.design.theme

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow


fun Modifier.defaultShadow() =
    shadow(elevation4, spotColor = Shadow, ambientColor = Shadow)



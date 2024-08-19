package com.test.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arpitkatiyarprojects.countrypicker.CountryPicker
import com.arpitkatiyarprojects.countrypicker.models.CountryDetails
import com.arpitkatiyarprojects.countrypicker.models.CountryPickerProperties
import com.arpitkatiyarprojects.countrypicker.models.Dimensions
import com.arpitkatiyarprojects.countrypicker.models.PickerTextStyles
import com.test.ui.design.theme.corner8
import com.test.ui.design.theme.itemHeight18
import com.test.ui.design.theme.itemHeight56
import com.test.ui.design.theme.itemWidth24
import com.test.ui.design.theme.padding4
import com.test.ui.design.theme.padding6

@Composable
fun CountryCode(modifier: Modifier = Modifier, countryDetails: (country:CountryDetails) -> Unit){
    CountryPicker (
        modifier =  modifier.height(itemHeight56).border(
            border = ButtonDefaults.outlinedButtonBorder,
            shape = RoundedCornerShape(corner8),
        ).background(
            Color.White,
            shape = RoundedCornerShape(corner8)
        ),
        defaultPaddingValues = PaddingValues(padding4, padding6),
        countryPickerProperties = CountryPickerProperties(),
        countryFlagDimensions = Dimensions(itemWidth24, itemHeight18),
        pickerTextStyles = PickerTextStyles(),
        onCountrySelected = countryDetails
    )
}
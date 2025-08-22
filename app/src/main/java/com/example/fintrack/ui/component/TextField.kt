package com.example.fintrack.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.R

@Composable
fun TextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    labelPadding: Dp = 8.dp,
    textColor: Color = BaseColor,
    semanticContentDescription: String = "",
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = FontFamily(Font(R.font.inter_semibold)),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle,
    iconColor: Color = BaseColor,
    placeholderColor : Color = BaseColor,
    focusedBorderColor: Color = BaseColor,
    leadingIconFocusedColor: Color = BaseColor,
    cursorColor: Color = BaseColor,
    unfocusedBorderColor: Color = BaseColor
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = textColor,
            fontSize = fontSize,
            fontFamily = fontFamily,
            modifier = Modifier.padding(top = labelPadding)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = semanticContentDescription },
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = placeholderColor,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = fontSize
                )
            },
            textStyle = textStyle,
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = iconColor)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = focusedBorderColor,
                cursorColor = cursorColor,
                unfocusedBorderColor = unfocusedBorderColor,
                focusedLeadingIconColor = leadingIconFocusedColor
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
    }
}
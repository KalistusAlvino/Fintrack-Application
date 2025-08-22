package com.example.fintrack.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.R

@Composable
fun PasswordTextField(
    label: String,
    textColor: Color = BaseColor,
    value: String,
    modifier: Modifier = Modifier,
    semanticContentDescription: String = "",
    placeholder: String = "",
    onValueChange: (String) -> Unit,
    fontSize: TextUnit = 14.sp,
    fontFamily: FontFamily = FontFamily(Font(R.font.poppins_regular)),
    labelPadding: Dp = 8.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    leadingIcon: ImageVector,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    textStyle: TextStyle
) {
    val showPassword = remember { mutableStateOf(false) }
    Column (
        modifier = modifier
            .fillMaxWidth()
    ) {
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
                    color = BaseColor,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    fontSize = fontSize
                )
            },
            textStyle = textStyle,
            leadingIcon = {
                Icon(imageVector = leadingIcon, contentDescription = null, tint = BaseColor)
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BaseColor,
                cursorColor = BaseColor,
                unfocusedBorderColor = BaseColor
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (showPassword.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(onClick = { showPassword.value = !showPassword.value }) {
                    Icon(
                        icon,
                        contentDescription = "Visibility",
                        tint = BaseColor
                    )
                }
            },
        )
    }
}
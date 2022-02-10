package com.example.swifty_component.utils

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextWithDescription(type: String, info: String) {
    Text(text = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
        ) {
            append("$type : ")
        }
        withStyle(
            SpanStyle(
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        ) {
            append(info)
        }
    })
}
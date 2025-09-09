package com.example.fintrack.presentation.main.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.fintrack.R
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse

@Composable
fun CategoryList(
    transactionCategory: GetTransactionCategoryResponse,
    onClick:(GetTransactionCategoryResponse) -> Unit
) {
    Row(
        modifier = Modifier
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
            .clickable {
                onClick(transactionCategory)
            }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = transactionCategory.imageUrl,
            contentDescription = "Income category",
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = transactionCategory.name,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "Expand",
            tint = Color.Gray,
            modifier = Modifier.size(14.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun CategoryListPreview() {
//    FintrackTheme {
//        CategoryList()
//    }
//}
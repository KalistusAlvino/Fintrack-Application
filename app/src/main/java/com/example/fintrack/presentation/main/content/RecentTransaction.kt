package com.example.fintrack.presentation.main.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.R
import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.ui.theme.MainColor

@Composable
fun RecentTransaction(
    transaction: GetMonthlySummaryResponse,
    boxIconColor: Color,
    icon: ImageVector
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 21.dp, top = 19.dp, bottom = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color(0xFFFFE2D6), // Peach background
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                contentDescription = "Income image",
                modifier = Modifier.size(20.dp),
                tint = boxIconColor
            )
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 4.dp, y = 4.dp) // agar tidak mentok ke pinggir
                    .background(color = boxIconColor, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Upload Icon",
                    tint = Color.White,
                    modifier = Modifier.size(9.dp)
                )
            }
        }
        Text(
            text = transaction.monthName,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 12.sp,
            lineHeight = 16.sp,
            modifier = Modifier
                .padding(start = 15.dp)
                .weight(1f)
        )
        Text(
            text = transaction.formattedTotal,
            fontFamily = FontFamily(Font(R.font.poppins_bold)),
            fontSize = 12.sp,
            lineHeight = 16.sp,
            modifier = Modifier
                .padding(end = 21.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun RecentTransactionPreview() {
//    FintrackTheme {
//        RecentTransaction(
//            boxIconColor = MainColor,
//            icon = Icons.Default.ArrowDownward
//        )
//    }
//}
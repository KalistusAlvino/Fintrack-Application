package com.example.fintrack.presentation.main.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.R
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.FintrackTheme

@Composable
fun SegmentedButton(
    selectedIndex: Int,
    items: List<String>,
    onSelectionChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    incomeColor: Color = MainColor,
    expensesColor: Color = AlertColor,
    unselectedColor: Color = Color.Transparent,
    selectedTextColor: Color = BaseColor,
    unselectedTextColor: Color = Color.Gray,
    cornerRadius: Dp = 15.dp
) {
    Box(
        modifier = modifier
            .background(
                color = BaseColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .border(width = 1.dp, color = MainColor, shape = RoundedCornerShape(cornerRadius))
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, item ->
                val isSelected = selectedIndex == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = when {
                                isSelected && selectedIndex == 0 -> incomeColor
                                isSelected && selectedIndex == 1 -> expensesColor
                                else -> unselectedColor
                            },
                            shape = RoundedCornerShape(cornerRadius - 4.dp)
                        )
                        .clickable {
                            onSelectionChange(index)
                        }
                        .padding(vertical = 12.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = item,
                        color = if (isSelected) selectedTextColor else unselectedTextColor,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_bold))
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SegmentedButtonPreview() {
    FintrackTheme {
        var selectedTab by remember { mutableIntStateOf(0) }
        val tabs = listOf("Income", "Expense")
        SegmentedButton(
            selectedIndex = selectedTab,
            items = tabs,
            onSelectionChange = { selectedTab = it },
            modifier = Modifier.fillMaxWidth()
        )
    }

}
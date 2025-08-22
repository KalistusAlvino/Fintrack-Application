package com.example.fintrack.presentation.main.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.fintrack.di.model.income.get.MonthlyIncomeResponse
import com.example.fintrack.ui.theme.BaseColor
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.component.shape.shader.fromBrush
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.component.shape.shader.DynamicShaders
import com.patrykandpatrick.vico.core.entry.entryModelOf

@Composable
fun MoneyChart(
    monthlyIncome: List<MonthlyIncomeResponse>,
    chartColor: List<Color>
) {
    val chartData = monthlyIncome.map { (it.totalIncome / 1_000).toFloat() }.toTypedArray()
    val chartEntryModel = entryModelOf(*chartData)
    val scrollState = rememberChartScrollState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                color = BaseColor,
            )
            .clipToBounds(),
        contentAlignment = Alignment.Center
    ) {
        Chart(
            chart = LineChart(
                lines = listOf(
                    LineChart.LineSpec(
                        lineColor = Color.Transparent.toArgb(),
                        lineBackgroundShader = DynamicShaders.fromBrush(
                            brush = Brush.verticalGradient(
                                chartColor
                            )
                        )
                    )
                )
            ),
            model = chartEntryModel,
            startAxis = null,
            bottomAxis = null,
            chartScrollState = scrollState,
            horizontalLayout = HorizontalLayout.fullWidth(),
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

//@Preview
//@Composable
//private fun LineChartPreview() {
//    FintrackTheme {
//        MoneyChart()
//    }
//}
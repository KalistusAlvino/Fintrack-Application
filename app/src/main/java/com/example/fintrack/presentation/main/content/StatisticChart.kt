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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.ui.theme.AlertColor
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.MainColor
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.layout.fullWidth
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.core.chart.layout.HorizontalLayout
import com.patrykandpatrick.vico.core.chart.line.LineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf

@Composable
fun StatisticChart(
    monthlyIncome: List<GetMonthlySummaryResponse>,
    monthlyExpenses: List<GetMonthlySummaryResponse>,
) {
// Buat entries dengan koordinat (x, y)
    val incomeEntries = monthlyIncome.mapIndexed { index, it ->
        entryOf(index.toFloat(), (it.total / 1_000f))
    }
    val expensesEntries = monthlyExpenses.mapIndexed { index, it ->
        entryOf(index.toFloat(), (it.total / 1_000f))
    }

// Gabungkan jadi multi-line model
    val chartEntryModel = entryModelOf(incomeEntries, expensesEntries)

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
                        lineColor = MainColor.toArgb(),

                    ),
                    LineChart.LineSpec(
                        lineColor = AlertColor.toArgb(),
                    ),
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
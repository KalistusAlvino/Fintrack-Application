package com.example.fintrack.presentation.main.home.transaction

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fintrack.R
import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.presentation.main.content.CategoryList
import com.example.fintrack.presentation.main.home.HomeViewModel
import com.example.fintrack.ui.component.Loading
import com.example.fintrack.ui.theme.BaseColor
import com.example.fintrack.ui.theme.BlurDark
import com.example.fintrack.ui.theme.MainColor
import com.example.fintrack.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun TransactionScreen(
    modifier: Modifier = Modifier,
    onEvent: (TransactionEvent) -> Unit,
    navigateToMain: () -> Unit,
    incomeCategory: IncomeCategoryResponse
) {
    var selectedCategory by rememberSaveable { mutableStateOf(incomeCategory) } // state
    //ViewModel
    //Category
    val viewModel: HomeViewModel = hiltViewModel()
    val incomeCategoryResult = viewModel.incomeState.value
    //transaction
    val transactionViewModel: TransactionViewModel = hiltViewModel()
    val result = transactionViewModel.transactionState.value

    //Input value
    var description by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    val amountLong = amount.toLongOrNull() ?: 0
    //Modal
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    //Validation
    val isFilled = description.isNotBlank() && amount.isNotBlank()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navigateToMain
                            },
                            modifier = Modifier
                                .border(
                                    width = 0.5.dp,
                                    shape = CircleShape,
                                    color = Color.Gray.copy(0.6f)
                                )
                                .size(32.dp)
                        ) {
                            Icon(
                                Icons.Default.ArrowBackIosNew,
                                contentDescription = "Back Home",
                                modifier = Modifier.size(14.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = "Add Transaction",
                            fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                            fontSize = 16.sp,
                            color = TextColor
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = BaseColor.copy(0.9f)
                ),
            )
        }
    ) { contentPadding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .background(color = BaseColor.copy(0.9f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextField(
                    value = amount,
                    onValueChange = { input ->
                        if (input.all { it.isDigit() }) {
                            amount = input
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    leadingIcon = {
                        Text(
                            text = "IDR",
                            fontFamily = FontFamily(Font(R.font.urbanist_semibold)),
                            color = TextColor
                        )
                    },
                    placeholder = {
                        Text(
                            text = "0",
                            fontSize = 32.sp,
                            color = Color.Gray.copy(0.5f),
                            fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                        )
                    },
                    textStyle = TextStyle( // Atur style teks utama
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_semibold))
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        cursorColor = TextColor
                    ),
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .fillMaxWidth()
                        .background(color = Color.Gray.copy(0.1f))
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp)
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
                        .defaultMinSize(minHeight = 56.dp), // Minimum height, bisa expand
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Default.Description,
                            contentDescription = "Description",
                            modifier = Modifier.padding(end = 16.dp),
                        )
                        BasicTextField(
                            value = description,
                            onValueChange = { description = it },
                            modifier = Modifier.weight(1f),
                            textStyle = TextStyle(
                                fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                                color = TextColor,
                                fontSize = 16.sp
                            ),
                            cursorBrush = SolidColor(TextColor),
                            decorationBox = { innerTextField ->
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.CenterStart // atau Alignment.Center untuk center horizontal juga
                                ) {
                                    if (description.isEmpty()) {
                                        Text(
                                            text = "Description",
                                            color = Color.Gray.copy(0.5f),
                                            fontSize = 16.sp,
                                            fontFamily = FontFamily(Font(R.font.urbanist_medium))
                                        )
                                    }
                                    innerTextField()
                                }
                            }
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .padding(top = 16.dp)
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
                            showBottomSheet = true
                        }
                        .defaultMinSize(minHeight = 56.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = "Category",
                        modifier = Modifier.padding(end = 16.dp),
                    )
                    Text(
                        text = selectedCategory.name,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.urbanist_medium)),
                        modifier = Modifier.weight(1f),
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "Expand",
                        tint = Color.Gray,
                        modifier = Modifier.size(14.dp)
                    )
                }
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 24.dp, vertical = 24.dp),
                    contentAlignment = Alignment.BottomCenter
                ){
                    Button(
                        onClick = {
                            onEvent(TransactionEvent.postIncome(incomeCategory.id, amountLong, description))
                        },
                        enabled = isFilled,
                        modifier = Modifier.fillMaxWidth()
                            .size(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MainColor,
                        )
                    ) {
                        Text(
                            text = "Save",
                            fontFamily = FontFamily(Font(R.font.urbanist_bold)),
                            fontSize = 16.sp,
                        )
                    }
                }

            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Select Category",
                        fontFamily = FontFamily(Font(R.font.poppins_bold)),
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            incomeCategoryResult.incomeCategory?.let {
                                it.forEach { incomeCategory ->
                                    CategoryList(
                                        incomeCategory = incomeCategory,
                                        onClick = {
                                            selectedCategory = incomeCategory
                                            showBottomSheet = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        result.data?.let {
            if (result.success) {
                navigateToMain()
            }
        }
        if (result.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = BlurDark)
                    .clickable { /* Prevent clicks */ },
                contentAlignment = Alignment.Center
            ) {
                Loading()
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun TransactionScreenPreview() {
//    FintrackTheme {
//        TransactionScreen()
//    }
//}
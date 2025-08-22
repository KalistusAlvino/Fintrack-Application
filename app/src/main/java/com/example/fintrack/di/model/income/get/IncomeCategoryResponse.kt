package com.example.fintrack.di.model.income.get

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IncomeCategoryResponse(
    val id:Int,
    val name: String,
    val imageUrl: String
): Parcelable

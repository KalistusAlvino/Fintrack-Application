package com.example.fintrack.data.remote

import com.example.fintrack.data.remote.dto.auth.LoginResponseDTO
import com.example.fintrack.data.remote.dto.auth.RegisterResponseDTO
import com.example.fintrack.data.remote.dto.auth.ResendVerifyResponseDTO
import com.example.fintrack.data.remote.dto.income.get.IncomeCategoryDTO
import com.example.fintrack.data.remote.dto.income.get.IncomeResponseDTO
import com.example.fintrack.data.remote.dto.income.get.MonthlyIncomeDTO
import com.example.fintrack.data.remote.dto.income.get.ThisMonthIncomeDTO
import com.example.fintrack.data.remote.dto.income.post.PostIncomeDTO
import com.example.fintrack.data.remote.dto.user.WalletResponseDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FintrackApi {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String
    ): RegisterResponseDTO

    @FormUrlEncoded
    @POST("resend-verification")
    suspend fun resendVerification(
        @Field("email") email: String
    ): ResendVerifyResponseDTO

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginResponseDTO

    @FormUrlEncoded
    @POST("wallet/income")
    suspend fun postIncome(
        @Field("category_id") categoryId: Int,
        @Field("amount") amount: Long,
        @Field("description") description: String
    ): PostIncomeDTO

    @GET("wallet")
    suspend fun getWallet(): WalletResponseDTO

    @GET("wallet/income")
    suspend fun getIncome(): IncomeResponseDTO

    @GET("wallet/this-month-income")
    suspend fun thisMonthIncome(): ThisMonthIncomeDTO

    @GET("wallet/monthly-income")
    suspend fun monthlyIncome(): MonthlyIncomeDTO

    @GET("wallet/income-category")
    suspend fun incomeCategory(): IncomeCategoryDTO
}
package com.example.fintrack.data.remote

import com.example.fintrack.data.remote.dto.auth.LoginResponseDTO
import com.example.fintrack.data.remote.dto.auth.RegisterResponseDTO
import com.example.fintrack.data.remote.dto.auth.ResendVerifyResponseDTO
import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.data.remote.dto.transaction.GetMonthlySummaryDTO
import com.example.fintrack.data.remote.dto.transaction.GetThisMonthSummaryDTO
import com.example.fintrack.data.remote.dto.transaction.GetTransactionCategoryDTO
import com.example.fintrack.data.remote.dto.transaction.GetTransactionDTO
import com.example.fintrack.data.remote.dto.transaction.PostTransactionDTO
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
    ): BaseResponse<PostTransactionDTO>

    @FormUrlEncoded
    @POST("wallet/expenses")
    suspend fun postExpenses(
        @Field("category_id") categoryId: Int,
        @Field("amount") amount: Long,
        @Field("description") description: String
    ): BaseResponse<PostTransactionDTO>
    //User Wallet
    @GET("wallet")
    suspend fun getWallet(): WalletResponseDTO
    //User Income
    @GET("wallet/income")
    suspend fun getIncome(): BaseResponse<List<GetTransactionDTO>>

    @GET("wallet/this-month-income")
    suspend fun thisMonthIncome(): BaseResponse<GetThisMonthSummaryDTO>

    @GET("wallet/this-month-expenses")
    suspend fun thisMonthExpenses(): BaseResponse<GetThisMonthSummaryDTO>

    @GET("wallet/monthly-income")
    suspend fun monthlyIncome(): BaseResponse<List<GetMonthlySummaryDTO>>

    @GET("wallet/monthly-expenses")
    suspend fun monthlyExpenses(): BaseResponse<List<GetMonthlySummaryDTO>>

    @GET("wallet/income-category")
    suspend fun getIncomeCategory(): BaseResponse<List<GetTransactionCategoryDTO>>

    //User Expense
    @GET("wallet/expenses")
    suspend fun getExpenses(): BaseResponse<List<GetTransactionDTO>>

    @GET("wallet/expenses-category")
    suspend fun getExpensesCategory(): BaseResponse<List<GetTransactionCategoryDTO>>
}
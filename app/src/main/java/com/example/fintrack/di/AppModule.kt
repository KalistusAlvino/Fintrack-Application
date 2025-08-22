package com.example.fintrack.di

import android.app.Application
import com.airbnb.lottie.BuildConfig
import com.example.fintrack.data.manger.LocalUserMangeImpl
import com.example.fintrack.data.pref.UserPreference
import com.example.fintrack.data.remote.AuthInterceptor
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.di.repository.auth.AuthRepositoryImpl
import com.example.fintrack.di.repository.auth.LoginRepositoryImpl
import com.example.fintrack.di.repository.auth.ResendVerifyRepositoryImpl
import com.example.fintrack.di.repository.income.IncomeRepositoryImpl
import com.example.fintrack.di.repository.user.WalletRepositoryImpl
import com.example.fintrack.domain.di.auth.AuthRepository
import com.example.fintrack.domain.di.auth.LoginRepository
import com.example.fintrack.domain.di.auth.ResendVerifyRepository
import com.example.fintrack.domain.di.income.IncomeRepository
import com.example.fintrack.domain.di.user.WalletRepository
import com.example.fintrack.domain.manger.LocalUserManger
import com.example.fintrack.domain.usecase.appentry.AppEntryUseCases
import com.example.fintrack.domain.usecase.appentry.ReadOnBoardingStatus
import com.example.fintrack.domain.usecase.appentry.SaveOnBoardingStatus
import com.example.fintrack.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providerRetrofit(authInterceptor: AuthInterceptor) : Retrofit{
        val loggingInterceptor =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManger = LocalUserMangeImpl(context = application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ): AppEntryUseCases = AppEntryUseCases(
        readOnBoardingStatus = ReadOnBoardingStatus(localUserManger),
        saveOnBoardingStatus = SaveOnBoardingStatus(localUserManger)
    )

    @Provides
    @Singleton
    fun provideFintrackApi(retrofit: Retrofit): FintrackApi {
        return retrofit.create(FintrackApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(fintrackApi: FintrackApi) : AuthRepository {
        return AuthRepositoryImpl(fintrackApi)
    }

    @Provides
    @Singleton
    fun provideResendVeriyRepositoryImpl(fintrackApi: FintrackApi) : ResendVerifyRepository {
        return ResendVerifyRepositoryImpl(fintrackApi)
    }

    @Provides
    @Singleton
    fun provideLoginRepositoryImpl(fintrackApi: FintrackApi, userPreference: UserPreference): LoginRepository {
        return LoginRepositoryImpl(fintrackApi, userPreference)
    }

    @Provides
    @Singleton
    fun provideWalletRepositoryImpl(fintrackApi: FintrackApi): WalletRepository {
        return WalletRepositoryImpl(fintrackApi)
    }

    @Provides
    @Singleton
    fun provideIncomeRepositoryImpl(fintrackApi: FintrackApi): IncomeRepository {
        return IncomeRepositoryImpl(fintrackApi)
    }


}
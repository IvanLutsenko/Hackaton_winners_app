package com.example.hackathonwinnersapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.hackathonwinnersapp.data.network.api.OrdersApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.awac.knowledgebase.app.di.JsonConverterModule
import javax.inject.Singleton

@Module(includes = [JsonConverterModule::class])
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideHttpBuilder(
        @ApplicationContext context: Context,
    ) = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(CHUCKER_CONTENT_LENGTH)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        .followSslRedirects(true)

    @Provides
    @Singleton
    fun provideHttpClient(
        builder: OkHttpClient.Builder,
    ) = builder.build()

    @Provides
    fun provideRetrofitBuilder(gson: Gson, httpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)

    @Provides
    @Singleton
    fun provideOrdersApiService(builder: Retrofit.Builder): OrdersApiService {
        return builder
            .baseUrl("https://www.to.do")
            .build()
            .create(OrdersApiService::class.java)
    }

    companion object {
        const val CHUCKER_CONTENT_LENGTH = 250000L
    }
}

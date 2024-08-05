package com.test.chatapp.di

import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import com.test.data.network.api.UsersApi
import com.test.chatapp.BuildConfig

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {


    @Singleton
    @Provides
    fun provideRetrofit( @ApiUrl apiUrl: String,
                         @ApiVersion apiVersion: String,
                         okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(apiUrl + apiVersion)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): UsersApi {
        return retrofit.create(UsersApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }


    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }


    @Provides
    @ApiUrl
    fun provideBaseApiUrl(): String {
        return BuildConfig.BASE_API_URL
    }

    @Provides
    @ApiVersion
    fun provideApiVersion(): String {
        return BuildConfig.API_VERSION
    }
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiUrl

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApiVersion
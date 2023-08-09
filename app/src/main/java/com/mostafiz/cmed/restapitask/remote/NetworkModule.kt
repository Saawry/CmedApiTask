package com.mostafiz.cmed.restapitask.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mostafiz.cmed.restapitask.di.Qualifiers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.net.ssl.*


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideOkHttp() : OkHttpClient{
        return OkHttpClient.Builder()
            .build()
    }



    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String {
        return "https://hp-api.onrender.com"
    }




    @Provides
    @Qualifiers.BaseCloud
    fun provideRetrofit(okHttpClient: OkHttpClient,@Named("baseUrl") baseUrl:String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


    @Provides
    fun provideApiClient(@Qualifiers.BaseCloud retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}
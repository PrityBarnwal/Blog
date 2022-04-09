package com.coolapps.travelblogapp.di

import com.coolapps.travelblogapp.network.BlogApi
import com.coolapps.travelblogapp.repository.BlogRepository
import com.coolapps.travelblogapp.repository.GetBlogUse
import com.coolapps.travelblogapp.repository.GetBlogUseCase
import com.coolapps.travelblogapp.repository.IBlogRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/3eede691af3e8ff795bf6d31effb873d484877be/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBlogService(retrofit: Retrofit): BlogApi{
        return retrofit.create(BlogApi::class.java)
    }

    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt{

        @Binds
        @Singleton
        fun provideBlogRepository(repo: BlogRepository): IBlogRepository

        @Binds
        @Singleton
        fun provideGetBlogUseCase(uc: GetBlogUse): GetBlogUseCase

    }


}

    /*

    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()

        okHttpClient.callTimeout(40, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(40, TimeUnit.SECONDS)
        okHttpClient.readTimeout(40, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(40, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)
        okHttpClient.build()
        return okHttpClient.build()
    }

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient, baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideRestApiService(retrofit: Retrofit): BlogApi {
        return retrofit.create(BlogApi::class.java)
    }

     */
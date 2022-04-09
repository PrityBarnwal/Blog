package com.coolapps.travelblogapp.network

import com.coolapps.travelblogapp.model.Blog
import com.coolapps.travelblogapp.model.Data
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface BlogApi {
    @GET("blog_articles.json")
    suspend fun getAllBlogs():Blog

    @GET("blog_articles.json/{dataId}")
    suspend fun getBlogInfo(@Path("dataId") dataId: String): Blog


}
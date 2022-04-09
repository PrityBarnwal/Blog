package com.coolapps.travelblogapp.repository


import com.coolapps.travelblogapp.data.Resource
import com.coolapps.travelblogapp.model.Blog
import com.coolapps.travelblogapp.model.Data
import com.coolapps.travelblogapp.network.BlogApi
import javax.inject.Inject


interface IBlogRepository{
    suspend fun getAllBlog(): Blog
}

class BlogRepository @Inject constructor(
    val api: BlogApi
): IBlogRepository {

    override suspend fun getAllBlog():Blog {
        return api.getAllBlogs()
    }

}
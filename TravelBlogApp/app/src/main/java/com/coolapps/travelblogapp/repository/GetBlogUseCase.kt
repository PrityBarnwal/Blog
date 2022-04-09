package com.coolapps.travelblogapp.repository

import com.coolapps.travelblogapp.model.Blog
import com.coolapps.travelblogapp.network.BlogApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Inject

interface GetBlogUseCase {
    suspend operator fun invoke(): Blog
}

class GetBlogUse @Inject constructor(
    val repo: BlogRepository
): GetBlogUseCase {
    override suspend fun invoke(): Blog {
        return repo.getAllBlog()
    }
}

package com.coolapps.travelblogapp.model

import com.google.firebase.firestore.PropertyName

data class MBlog(var id: String? = null,
                 var title: String? = null,
                 var authors: String? = null,
                 var description:String?=null,
                 var rating: Double?=null,
                 @get:PropertyName("google_blog_id")
                 @set:PropertyName("google_blog_id")
                 var googleBookId: String? = null,
                 @get:PropertyName("user_id")
                 @set:PropertyName("user_id")
                 var userId: String? = null,





)

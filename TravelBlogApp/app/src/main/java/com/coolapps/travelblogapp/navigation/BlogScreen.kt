package com.coolapps.travelblogapp.navigation

enum class BlogScreen {
    LoginScreen,
    DetailScreen,
    BlogHomeScreen;
    companion object{
        fun fromRoute(route: String?): BlogScreen
                = when(route?.substringBefore("/")){
            LoginScreen.name ->LoginScreen
            DetailScreen.name -> DetailScreen
            BlogHomeScreen.name -> BlogHomeScreen
            null -> BlogHomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }

}
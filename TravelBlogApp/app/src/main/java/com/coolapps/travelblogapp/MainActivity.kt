package com.coolapps.travelblogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.coolapps.travelblogapp.data.Resource
import com.coolapps.travelblogapp.navigation.BlogNavigation
import com.coolapps.travelblogapp.screen.home.HomeScreenViewModel
import com.coolapps.travelblogapp.ui.theme.TravelBlogAppTheme
import com.google.accompanist.insets.statusBarsPadding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelBlogAppTheme {
                BlogApp()
            }
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun BlogApp(){
    Surface(color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            BlogNavigation()
        }
    }
}

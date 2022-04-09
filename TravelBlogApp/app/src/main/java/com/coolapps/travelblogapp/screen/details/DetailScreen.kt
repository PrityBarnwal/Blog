package com.coolapps.travelblogapp.screen.details


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.coolapps.travelblogapp.R
import com.coolapps.travelblogapp.model.Data
import com.coolapps.travelblogapp.navigation.BlogScreen
import com.coolapps.travelblogapp.screen.home.HomeScreenViewModel
import com.coolapps.travelblogapp.screen.home.TravelAppBar

@Composable
fun DetailScreen(navController: NavController,
                 viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        TravelAppBar(
            title = "Blog Details",
            icon = Icons.Default.ArrowBack,
            showProfile = false,
            navController = navController
        )
    }) {

        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize()
        ) {

           BlogDesciption(navController = navController)
        }
    }
}

@Composable
fun BlogDesciption(viewModel: HomeScreenViewModel = hiltViewModel(),
             navController: NavController
){
    val listOfCategories by remember { viewModel.listOfCategories }
    Column {



        LazyColumn{
            items(listOfCategories){ item ->
                BlogData(data = item,navController)

            }
        }

    }
}
@Composable
fun BlogData(data: Data,
                   navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
                Text(text = data.description, overflow = TextOverflow.Ellipsis)
        }
    }


package com.coolapps.travelblogapp.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.coolapps.travelblogapp.model.Data
import com.coolapps.travelblogapp.navigation.BlogScreen
import com.coolapps.travelblogapp.R


@ExperimentalComposeUiApi
@Composable
fun HomeScreen(navController: NavController,viewModel: HomeScreenViewModel) {
    Scaffold(topBar = {
        TravelAppBar(title = "TravelBlog", navController = navController)
        Spacer(modifier = Modifier.height(13.dp))
    }) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                SearchBar(
                    hint = "Search...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                   // viewModel.searchBlogList(it)
                }
                Spacer(modifier = Modifier.height(13.dp))

                BlogData(navController = navController)
            }
        }
    }
}
@Composable
fun BlogData(viewModel: HomeScreenViewModel = hiltViewModel(),
             navController: NavController
){
    val listOfCategories by remember { viewModel.listOfCategories }
    Column {
        


        LazyColumn{
            items(listOfCategories){ item ->
              BlogCollection(data = item,navController)

            }
        }

    }
}
@Composable
fun BlogCollection(data: Data,
                   navController: NavController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {navController.navigate(BlogScreen.DetailScreen.name + "/${data.id}") },           // handling onCategoryClick
        elevation = 8.dp,
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(80.dp), painter = rememberImagePainter(
                    data.image,
                    builder = {
                        scale(Scale.FILL)
                        placeholder(R.drawable.placeholder)
                        transformations(CircleCropTransformation())

                    }
                ), contentDescription = null
            )
            Column {
                Text(text = data.title, overflow = TextOverflow.Ellipsis)
                Text(text = data.author.name, overflow = TextOverflow.Ellipsis)
            }
        }
    }
}
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    //  isHintDisplayed = it != FocusState && text.isNotEmpty()
                }
        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}
@Composable
fun TravelAppBar(
    title: String,
    icon: ImageVector? = null,
    showProfile: Boolean = true,
    navController: NavController,
    onBackArrowClicked:() -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showProfile) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Logo Icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .scale(0.9f)
                    )

                }
                if (icon != null) {
                    Icon(imageVector = icon,
                        contentDescription = "arrow back",
                        tint = Color.Red.copy(alpha = 0.7f),
                        modifier = Modifier.clickable { onBackArrowClicked.invoke() })
                }
                Spacer(
                    modifier = Modifier.width(
                        40.dp
                    )
                )
                Text(
                    text = title,
                    color = Color.Red.copy(alpha = 0.7f),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )

            }

        },
        actions = {
            IconButton(onClick = {
                FirebaseAuth.getInstance()
                    .signOut().run {
                        navController.navigate(BlogScreen.LoginScreen.name)
                    }
            }) {
                if (showProfile) Row() {
                    Icon(
                        imageVector = Icons.Filled.Logout,
                        contentDescription = "Logout"
                    )
                } else Box {}


            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )
}


/*
@ExperimentalComposeUiApi
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}) {
    Column {
        val searchQueryState = rememberSaveable { mutableStateOf("") }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(searchQueryState.value) {
            searchQueryState.value.trim().isNotEmpty()

        }
        InputField(valueState = searchQueryState,
            labelId = "Search",
            enabled = true,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            })
    }
}


 */


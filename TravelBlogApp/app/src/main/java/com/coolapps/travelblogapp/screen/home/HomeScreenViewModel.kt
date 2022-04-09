package com.coolapps.travelblogapp.screen.home



import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.coolapps.travelblogapp.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coolapps.travelblogapp.data.Resource
import com.coolapps.travelblogapp.model.Blog
import com.coolapps.travelblogapp.model.Data
import com.coolapps.travelblogapp.repository.GetBlogUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class HomeScreenViewModel @Inject constructor(usecase: GetBlogUseCase
): ViewModel() {

    /*
    private var isSearchStarting = true
    var isSearching = mutableStateOf(true)
    private var cachedBlogList = listOf<Data>()
    var blogList = mutableStateOf<List<Data>>(listOf())

     */

    private val _listOfCategories: MutableState<List<Data>> = mutableStateOf(emptyList())
    val listOfCategories: State<List<Data>> = _listOfCategories
    init {
        viewModelScope.launch {
            val blogList = usecase()
            _listOfCategories.value = blogList.data
        }
    }
    /*
    fun searchBlogList(query: String){
        val listToSearch = if (isSearchStarting){
            blogList.value
        }else
        {
            cachedBlogList
        }
        viewModelScope.launch (Dispatchers.Default){
            if (query.isEmpty()){
                blogList.value = cachedBlogList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch?.filter{
                it.title.contains(query.trim(),ignoreCase = true)
            }
            if (isSearchStarting){
                cachedBlogList = blogList.value
                isSearchStarting = false
            }
            blogList.value= results
            isSearching.value =true
        }
    }


     */

}
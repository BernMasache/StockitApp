package com.example.stockitapp.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockitapp.model.CollectionModel
import com.example.stockitapp.network.ApiService
import kotlinx.coroutines.launch

class CollectionViewModel: ViewModel() {
    var collectionsResponse:List<CollectionModel> by mutableStateOf(listOf())
    var error: String by mutableStateOf("")

    fun getCollections(){
        viewModelScope.launch {
            val apiService= ApiService.getInstance()
            try {
                var collections = apiService.getCollections()
                collectionsResponse = collections
            }catch (e:Exception){
                error=e.message.toString()
            }
        }
    }
}
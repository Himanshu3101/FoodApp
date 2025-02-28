package com.example.foodapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.Domain.BannerModel
import com.example.foodapp.Domain.CategoryModel
import com.example.foodapp.Domain.FoodModel
import com.example.foodapp.Repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _bannerState = MutableStateFlow<List<BannerModel>>(emptyList())
    val bannerState: Flow<List<BannerModel>> = _bannerState

    private val _categoryState = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categoryState: Flow<List<CategoryModel>> = _categoryState

    private val _loadFilter = MutableStateFlow<List<FoodModel>>(emptyList())
    val loadFilter: StateFlow<List<FoodModel>> = _loadFilter.asStateFlow()



    init {
        loadBanner()
        loadCategory()
    }

    fun loadBanner() {
        viewModelScope.launch {
            repository.loadBanner()
                .catch { e -> e.printStackTrace() } // Handle Error
                .collect { banners -> _bannerState.value = banners }   //CollectFlow
        }
    }

    fun loadCategory() {
        viewModelScope.launch {
            repository.loadCategory()
                .catch { e -> e.printStackTrace() }
                .collect{category -> _categoryState.value = category}
        }
    }

    fun loadFilter(id:String) {
        viewModelScope.launch {
            repository.loadFilter(id)
                .catch { e -> e.printStackTrace() }
                .collect{loadFilter -> _loadFilter.value = loadFilter}
        }
    }



}
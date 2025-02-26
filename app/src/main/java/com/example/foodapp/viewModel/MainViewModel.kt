package com.example.foodapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.Domain.BannerModel
import com.example.foodapp.Repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel(){

    private val _bannerState = MutableStateFlow<List<BannerModel>>(emptyList())
    val bannerState : Flow<List<BannerModel>> = _bannerState


    init{
        loadBanner()
    }

    fun loadBanner() {
        viewModelScope.launch {
            repository.loadBanner()
                .catch { e -> e.printStackTrace() } // Handle Error
                .collect{banners -> _bannerState.value = banners}   //CollectFlow
        }
    }

}
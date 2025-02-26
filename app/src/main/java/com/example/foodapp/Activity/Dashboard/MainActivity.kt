package com.example.foodapp.Activity.Dashboard

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import com.example.foodapp.Activity.BaseActivity
import com.example.foodapp.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()){
    Log.d("Debug", "MainScreen Composable is called")

//    val banners = remember { mutableStateListOf<BannerModel>() }

    val banners by viewModel.bannerState.collectAsStateWithLifecycle(initialValue = emptyList())

    var showBannerLoading by remember { mutableStateOf(true) }

    LaunchedEffect(banners) {
       showBannerLoading = banners.isEmpty()
    }

    Scaffold (bottomBar = { MyBottomBar() }){ paddingValues ->
        LazyColumn (
            modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
        ){
            item {
                TopBar()
            }
            item {
                Banner(banners.toMutableStateList(), showBannerLoading)
            }
            item {
                Search()
            }
        }
    }
}
package com.example.foodapp.Activity.ItemsList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.foodapp.Domain.FoodModel
import com.example.foodapp.R


@Composable
fun ItemsList(items: List<FoodModel>){
    LazyColumn (modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)){
        itemsIndexed(items) { index, item ->
            Items(item = item, index = index)
        }
    }
}

@Composable
fun Items(item: FoodModel, index: Int) {
    val context = LocalContext.current
    val isEvenRow = index % 2 == 0

    Row(
        modifier = Modifier.padding(vertical = 8.dp)
            .fillMaxWidth()
            .background(colorResource(R.color.grey), shape = RoundedCornerShape(10.dp))
            .wrapContentHeight()
            .clickable {

            }
    ){
        if(isEvenRow){
            FoodImage(item = item)
        }else{

        }
    }
}

@Composable
fun FoodImage(item: FoodModel) {

}

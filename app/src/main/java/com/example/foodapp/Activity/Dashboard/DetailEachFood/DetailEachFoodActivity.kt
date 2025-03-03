package com.example.foodapp.Activity.Dashboard.DetailEachFood

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.foodapp.Activity.BaseActivity
import com.example.foodapp.Domain.FoodModel
import com.uilover.project2142.Helper.ManagmentCart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailEachFoodActivity : BaseActivity() {

    private lateinit var item: FoodModel
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        item = intent.getSerializableExtra("object") as FoodModel
        item.numberInCart = 1
        managmentCart = ManagmentCart(this)

        setContent {
            DetailScreen(
                item,
                onBackClick = { finish() },
                onAddTOCartClick = { managmentCart.insertItem(item) })
        }
    }
}

@Composable
private fun DetailScreen(item: FoodModel, onAddTOCartClick: () -> Unit, onBackClick: () -> Unit) {
    var numberInCart by remember { mutableStateOf(item.numberInCart) }

    ConstraintLayout {
        val (footer, column) = createRefs()
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .constrainAs(column) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                start.linkTo(parent.start)
            }
            .padding (bottom = 80.dp)
        )
        {
            HeaderSection(
                item=item,
                numberInCart = numberInCart,
                onBackClick = onBackClick,
                onIncrement={

                },
                onDecrement={

                }
            )
        }
    }
}


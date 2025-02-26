package com.example.foodapp.Activity.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Search() {
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        placeholder = if (text.isEmpty()) { // Hide label when typing
            {
                Text(
                    text = "Search",
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                )
            }
        } else null,
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = null,
                modifier = Modifier.size(22.dp)
            )
        }, shape = RoundedCornerShape(10.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(R.color.grey),  // Background color when focused
            unfocusedContainerColor = colorResource(R.color.grey),  // Background color when unfocused
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
//            focusedLabelColor = Color.DarkGray,
//            unfocusedLabelColor = Color.DarkGray,
            cursorColor = Color.DarkGray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp)
            .background(colorResource(R.color.grey), CircleShape)
    )
}

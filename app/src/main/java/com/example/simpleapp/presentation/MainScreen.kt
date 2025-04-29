package com.example.simpleapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import kotlin.math.max

@Composable
fun MainScreen(
    onNavigation: () -> Unit
) {
    
    var data by remember { 
        mutableStateOf("")
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column {
            TextField(
                value = data,
                onValueChange =  {value ->
                    if (value.all { it.isDigit() }) {
                        data = value
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done

                ),
                singleLine = true,
                maxLines = 1
            )
            Button(
                modifier = Modifier.testTag("toTheListButton"),
                onClick = onNavigation
            ) {
                Text(text = "Go to List Screen")
            }
        }

    }
}
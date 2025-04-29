package com.example.simpleapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.simpleapp.domain.model.SimpleItem

@Composable
fun ListScreen(toItem: (SimpleItem) -> Unit) {


    val items = buildList {
        add(SimpleItem(name = "Bike", description = "This is a MTB", price = 1.22))
        add(SimpleItem(name = "Car", description = "This is a Car", price = 5.00))
        add(SimpleItem(name = "Boat", description = "This is a Boat", price = 3.75))
        add(SimpleItem(name = "Scooter", description = "This is a Scooter", price = 2.11))
    }

    LazyColumn {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .testTag("item_${item.name}")
                    .fillMaxWidth()
                    .height(44.dp)
                    .padding(horizontal = 22.dp)
                    .clickable {
                        toItem(item)
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = item.name)
                Text(text = item.price.toString())
            }
        }
    }

}


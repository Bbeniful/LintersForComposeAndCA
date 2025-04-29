package com.example.simpleapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.testTag
import com.bbeniful.annotation_processor.annotation.SkipSetTagCheck
import com.example.simpleapp.data.FakeWrongObject
import com.example.simpleapp.domain.model.SimpleItem

@Composable
@SkipSetTagCheck
fun DetailsUI(item: SimpleItem, onBack: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Back",
            modifier = Modifier
                .testTag("back")
                .clickable { onBack() }
                .align(Alignment.TopStart))

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.testTag("title_${item.name}"), text = item.name
            )
            Text(
                modifier = Modifier.testTag("description_${item.name}"), text = item.description
            )
            Text(text = "${item.price}")
            Asd()
            ShouldGetWarning()

        }
    }
}

@Composable
fun Asd(){
    Row(
        modifier = Modifier
    ) {
        Text(
            modifier = Modifier,
            text = "Joska")
    }
}

@Composable
@SkipSetTagCheck
fun ShouldGetWarning() {
    val name = ""
    Box(modifier = Modifier.testTag("")) {
        Text(text = name, modifier = Modifier)
    }
}

fun something() {
    val fakeObj = FakeWrongObject()
}



@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.skipTestTag() = composed { this }
package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.youtubemusicclone.common.Constants.CATEGORIES

@Composable
fun CategoriesWidget() {
    val selectedCategory = rememberSaveable { mutableStateOf(CATEGORIES.first()) }

    LazyRow(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(CATEGORIES) {
            CategoryButton(text = it, isSelected = it == selectedCategory.value) {
                selectedCategory.value = it
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun CategoriesWidgetPreview() {
    CategoriesWidget()
}
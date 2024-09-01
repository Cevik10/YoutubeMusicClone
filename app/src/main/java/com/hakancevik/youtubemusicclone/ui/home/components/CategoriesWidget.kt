package com.hakancevik.youtubemusicclone.ui.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hakancevik.data.model.Category
import com.hakancevik.domain.entity.playlistdata.TrackData
import com.hakancevik.youtubemusicclone.R

@Composable
fun CategoriesWidget(
    tracks: List<TrackData>,
    playlistTitle: String,
    onTrackClick: (TrackData) -> Unit
) {
    val selectedCategory = rememberSaveable { mutableStateOf<Category?>(null) }
    val lazyListState = rememberLazyListState()

    // Back tuşu davranışını yönetme
    BackHandler(enabled = selectedCategory.value != null) {
        selectedCategory.value = null
    }

    // Seçili kategori değiştiğinde kaydırma işlemi
    LaunchedEffect(selectedCategory.value) {
        selectedCategory.value?.let { category ->
            val index = Category.entries.indexOf(category)
            val safeIndex = if (index > 0) index - 1 else 0
            lazyListState.animateScrollToItem(safeIndex)
        } ?: run {
            // Seçili kategori yoksa, varsayılan ilk pozisyona dön
            lazyListState.animateScrollToItem(0)
        }
    }

    LazyRow(
        state = lazyListState,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(Category.entries.toTypedArray()) { category ->
            val isSelected = category == selectedCategory.value
            CategoryButton(
                text = category.displayName,
                isSelected = isSelected
            ) {
                selectedCategory.value = if (isSelected) null else category
            }
        }
    }

    // İçerik değişimi
    when (selectedCategory.value) {
        null -> HomeScreenContent(tracks, playlistTitle, onTrackClick) // Varsayılan içerik
        else -> CategoryScreenContent(selectedCategory.value!!)
    }
}


@Composable
fun HomeScreenContent(
    tracks: List<TrackData>,
    playlistTitle: String,
    onTrackClick: (TrackData) -> Unit
) {
    TracksSection(title = playlistTitle) {
        TracksGrid(tracks = tracks, onTrackClick = onTrackClick)
    }
}

@Composable
fun CategoryScreenContent(category: Category) {
    when (category) {
        Category.ROMANCE -> {
            Text(text = "Romantic movies and music")
        }

        Category.ENERGIZE -> {
            Text(text = "Energizing playlists to get you moving")
        }

        Category.FEEL_GOOD -> {
            Text(text = "Feel good vibes and uplifting content")
        }

        Category.RELAX -> {
            Text(text = "Relaxing sounds and peaceful scenes")
        }

        Category.WORKOUT -> {
            Text(text = "Music and videos to boost your workout")
        }

        Category.SAD -> {
            Text(text = "Songs and scenes for when you're feeling down")
        }

        Category.COMMUTE -> {
            Text(text = "Content to enjoy on your way to work or school")
        }

        Category.PARTY -> {
            Text(text = "Party hits and fun playlists")
        }

        Category.FOCUS -> {
            Text(text = "Music and videos to help you concentrate")
        }

        Category.SLEEP -> {
            Text(text = "Calm sounds and scenes to help you sleep")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CategoriesWidgetPreview() {
    //CategoriesWidget()
}
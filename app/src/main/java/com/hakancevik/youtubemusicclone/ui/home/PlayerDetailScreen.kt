//package com.hakancevik.youtubemusicclone.ui.home
//
//import android.content.Context
//import android.util.Log
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.ModalBottomSheetState
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.layout.boundsInRoot
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.CoroutineScope
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun PlayerDetailScreen(
//    songListId: String,
//    serviceBinder: MusicPlayerService.MusicServiceBinder?,
//    context: Context = LocalContext.current,
//    sheetState: ModalBottomSheetState,
//    coroutineScope: CoroutineScope,
//    bottomSheetWidgetBounds: MutableState<Float?>
//) {
//    Log.d(
//        "MockUsage",
//        "Since the application is a mock application, data is received with the mock, without using the clicked $songListId."
//    )
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .onGloballyPositioned { coordinates ->
//            val toDp = pxToDp(context = context, coordinates.boundsInRoot().top)
//
//            if (toDp != 0f) {
//                bottomSheetWidgetBounds.value = toDp
//            }
//        }
//        .background(
//            brush = Brush.verticalGradient(
//                colors = listOf(
//                    MaterialTheme.colorScheme.onTertiary,
//                    MaterialTheme.colorScheme.onSurface,
//                    MaterialTheme.colorScheme.background
//                ), startY = Constants.DEFAULT_VALUE, endY = dpToPx(context, 1200f).toFloat()
//            )
//        )) {
//        Column {
//            PlayerDetailTopToolbar(sheetState, coroutineScope)
//            Spacer(modifier = Modifier.height(12.dp))
//            DynamicAsyncImage(
//                imageUrl = songListItem.playlistList.first().playlistList.first().iconUrl,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(12.dp)
//                    .clip(RoundedCornerShape(12.dp))
//            )
//            Spacer(modifier = Modifier.height(40.dp))
//            SongInformationWidget()
//            SliderSeekBar(serviceBinder)
//            ActionButtons(serviceBinder)
//        }
//    }
//}
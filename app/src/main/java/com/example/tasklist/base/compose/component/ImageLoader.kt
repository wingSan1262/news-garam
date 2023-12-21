package com.example.tasklist.base.compose.component

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.tasklist.base.extensions.getImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ImageUrlLoader(
    url: String = "",
    desc: String = ""
){

    Box(
        Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentAlignment = Alignment.Center
    ){
        RippleLoading()
        Image(
            rememberAsyncImagePainter(model = url),
            contentDescription = desc,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
    }

}

//@Composable
//fun LoadUrlImage(
//    url: String,
//    onLoading : @Composable (BoxScope, Float)->Unit = { scope, size ->
//        Column(verticalArrangement = Arrangement.Center){
//            RippleLoading()
//        }
//    },
//    onFailure : @Composable (Throwable)->Unit = {},
//    modifier: Modifier = Modifier
//        .fillMaxWidth().height(200f.dp)
//        .padding(8.dp)
//        .shadow(elevation = 8.dp, RoundedCornerShape(16.dp))
//        .background(Color.White, RoundedCornerShape(16.dp))
//        .clip(RoundedCornerShape(16.dp))
//){
//    KamelImage(
//        resource = asyncPainterResource(url),
//        contentDescription = null,
//        modifier = modifier,
//        contentScale = ContentScale.Crop,
//        onLoading = onLoading,
//        onFailure = { exception: Throwable ->
//            onFailure(exception)
//        },
//    )
//}
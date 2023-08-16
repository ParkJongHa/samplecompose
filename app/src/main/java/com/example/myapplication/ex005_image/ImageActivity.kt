package com.example.myapplication.ex005_image

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.AwaitPointerEventScope
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.PointerInputScope
import androidx.compose.ui.input.pointer.changedToDown
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.consumeDownChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.ads.interactivemedia.v3.internal.it
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.CancellationException

class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                ImageActivityScreen()
            }
        }
    }
}

@Composable
fun ImageActivityScreen() {
    val painter: AsyncImagePainter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data("https://i.namu.wiki/i/UOnP11W-Pyuo-KTxVnM-G5EN6uPvNQQS7Ce3LUQLceL9HX9S4UxsJTbxTEXha_EDGK7Fjwk5SHXXHOstnbRyjw.webp")
            .size(Size.ORIGINAL) // Set the target size to load the image at.
            .build()
    )

    Column(Modifier.fillMaxSize()) {
        Text("Img")
        Text(" ")
        Img(painter)
        Text(" ")
        CircleImg(painter)
        Text(" ")
        TransformableImg(painter)
    }
}

@Composable
fun Img(painter: AsyncImagePainter, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Image(
        modifier = modifier
            .clickable { Toast.makeText(context, "Img", Toast.LENGTH_SHORT).show() }
        ,
        painter = painter,
        contentDescription = ""
    )
}
@Composable
fun CircleImg(
    painter: AsyncImagePainter,
    modifier: Modifier = Modifier
        .size(64.dp)
        .clip(CircleShape)                       // clip to the circle shape
        .border(2.dp, Color.Gray, CircleShape)
) {
    val context = LocalContext.current

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = modifier
            .clickable { Toast.makeText(context, "CircleImg", Toast.LENGTH_SHORT).show() }
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TransformableImg(painter: AsyncImagePainter) {
    var scale by remember { mutableStateOf(1f) }
    var rotation by remember { mutableStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
    }

    var toState by remember { mutableStateOf(ComponentState.Released) }

    var isTapInProgress by remember { mutableStateOf(false) }

    if (toState == ComponentState.Released) {
        println("toState Released:$toState")
    } else {
        println("toState Pressed:$toState")
    }

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = "",
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                rotationZ = rotation,
                translationX = offset.x,
                translationY = offset.y
            )
            .transformable(state = state)
            .fillMaxWidth()
//            .clickable {
//                println("Tap completed")
//            }
//            .pointerInput(key1 = "someStringKey?") {
//                detectTapGestures(
//                    onPress = { offset ->
//                        // This block will be executed when the tap gesture starts
//                        toState = ComponentState.Pressed
//                        tryAwaitRelease()
//                        toState = ComponentState.Released
//                    },
//                    onTap = { offset ->
//                        // This block will be executed when the tap gesture ends
//                        if (isTapInProgress) {
//                            isTapInProgress = false
//                            println("Tap ended at position: $offset")
//                        }
//                    },
//
//
//                )
//
//                detectTapGesturesWithoutConsuming { offset ->
//                    println("Tap ended at position: $offset")
//                }
//            }
//            .pointerInput(Unit) {
//                detectTapGestures(
//                    onPress = {offset ->
//                        // This block will be executed when the tap gesture starts
//                        // You can perform any action here
//
//                        // The 'offset' parameter represents the position of the touch event
//                        println("Press detected at position: $offset")
//                        isTapInProgress = true
////                        tryAwaitRelease()
//
//                        // This block will be executed when the tap gesture ends
//                        // You can perform any action here
////                        println("Release detected at position: $offset")
//                    },
//                    onDoubleTap = {println("detectTapGestures onDoubleTap $scale, $rotation, $offset")},
//                    onLongPress = {println("detectTapGestures onLongPress $scale, $rotation, $offset")},
//                    onTap = {
//                        println("detectTapGestures onTap $scale, $rotation, $offset")
//                        isTapInProgress = false
//                    },
//
//                )
//            }
    )


        // apply other transformations like rotation and zoom
        // on the pizza slice emoji

//            detectDragGestures(
//                onDrag = { change: PointerInputChange, dragAmount: Offset -> Log.i("detectDragGestures onDrag", "$change $dragAmount") },
//                onDragStart = {offset -> Log.i("detectDragGestures onDragStart", "$offset")},
//                onDragEnd = {Log.i("detectDragGestures onDragEnd", "$offset")},
//                onDragCancel = {Log.i("detectDragGestures onDragCancel", "$offset")}
//            )
//        }
//        .pointerInput(key1 = "someStringKey?") {
//            detectTapGestures(
//                onPress = { Toast.makeText(mContext, "Press Detected", Toast.LENGTH_SHORT).show() },
//                onLongPress = { Toast.makeText(mContext, "Long Press Detected", Toast.LENGTH_SHORT).show() },
//                onTap = { Toast.makeText(mContext, "Tap Detected", Toast.LENGTH_SHORT).show() },
//                onDoubleTap = { Toast.makeText(mContext, "Double Tap Detected", Toast.LENGTH_SHORT).show() },
//                onRelease = { Toast.makeText(mContext, "Release Detected", Toast.LENGTH_SHORT).show() }
//            )
//        }
            /*
        .pointerInteropFilter {
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {Log.i("pointerInteropFilter ACTION_DOWN", "$it")}
                MotionEvent.ACTION_MOVE -> {Log.i("pointerInteropFilter ACTION_MOVE", "$it")}
                MotionEvent.ACTION_UP -> {Log.i("pointerInteropFilter ACTION_UP", "$it")}
                else -> {Log.i("pointerInteropFilter else", "$it")}
            }
            true
        }
        */
}
suspend fun PointerInputScope.detectTapGesturesWithoutConsuming(
    onTap: (Offset) -> Unit
) {
    awaitPointerEventScope {
        val down = awaitPointerEvent(PointerEventPass.Initial)
            .changes
            .firstOrNull { it.changedToDown() }

        val up = awaitPointerEvent(PointerEventPass.Initial)
            .changes
            .firstOrNull { it.changedToUp() }

        if (down != null && up != null) {
            onTap(up.position)
        }
    }
}

enum class ComponentState { Pressed, Released }
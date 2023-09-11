package com.example.myapplication.ex006_slide

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun SlideBox(
    modifier: Modifier,
    offsetMaxX: Dp = 0.dp,
    offsetMinX: Dp = 0.dp,
    standardX: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val offsetMaxX: Dp = 96.dp
    val offsetMinX: Dp = -96.dp
    val standardX: Dp = 0.dp

    val animMoveCnt = 10
    val animDelay = 10L
    var animJob: Job? = null

    Box(
        modifier = modifier
    ) {
        var offsetX by remember { mutableFloatStateOf( 0f ) }

        Box(
            Modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = { animJob?.cancel() },
                    onDrag = { _: PointerInputChange, dragAmount: Offset ->
                        var tOffsetX = offsetX + dragAmount.x
                        if (offsetMaxX.toPx() < tOffsetX) tOffsetX = offsetMaxX.toPx()
                        else if (tOffsetX < offsetMinX.toPx()) tOffsetX = offsetMinX.toPx()
                        offsetX =  tOffsetX
                    },
                    onDragCancel = { Log.e("onDragCancel", "-") },
                    onDragEnd = {
                        val minD = abs(offsetMinX.toPx() - offsetX)
                        val maxD = abs(offsetMaxX.toPx() - offsetX)
                        val directionX =
                            if (minD > maxD) offsetMaxX
                            else if (minD < maxD) offsetMinX
                            else standardX

                        if (offsetMinX == directionX) { // min 쪽에 치우쳐진 상태
                            if ( (abs(offsetMinX.toPx()-offsetX)) < ((abs(offsetMinX.toPx()-standardX.toPx()))/2) ) {
                                // Log.e("onDragEnd", "min > min")
                                animJob = CoroutineScope(Dispatchers.IO).launch {
                                    val distancePerCnt = (offsetMinX.toPx() - offsetX)/animMoveCnt
                                    var tOffset: Float

                                    for (i in 0 .. animMoveCnt) {
                                        delay(animDelay)
                                        tOffset = offsetX + distancePerCnt
                                        if (tOffset < offsetMinX.toPx()) tOffset = offsetMinX.toPx()
                                        offsetX = tOffset
                                    }

                                    offsetX = offsetMinX.toPx()
                                }
                            } else {
                                // Log.e("onDragEnd", "min > standardX")
                                animJob = CoroutineScope(Dispatchers.IO).launch {
                                    val distancePerCnt = (offsetMinX.toPx() - standardX.toPx())/animMoveCnt
                                    var tOffset: Float

                                    for (i in 0 .. animMoveCnt) {
                                        delay(animDelay)
                                        tOffset = offsetX - distancePerCnt
                                        if (standardX.toPx() < tOffset) tOffset = standardX.toPx()
                                        offsetX = tOffset
                                    }

                                    offsetX = standardX.toPx()
                                }
                            }
                        } else if (offsetMaxX == directionX) { // max 쪽에 치우쳐진 상태
                            if ( (abs(offsetMaxX.toPx()-offsetX)) < ((abs(offsetMaxX.toPx()-standardX.toPx()))/2) ) {
                                // Log.e("onDragEnd", "max > max")
                                animJob = CoroutineScope(Dispatchers.IO).launch {
                                    val distancePerCnt = (offsetMaxX.toPx() - offsetX)/animMoveCnt
                                    var tOffset: Float

                                    for (i in 0 .. animMoveCnt) {
                                        delay(animDelay)
                                        tOffset = offsetX + distancePerCnt
                                        if (offsetMaxX.toPx() < tOffset) tOffset = offsetMaxX.toPx()
                                        offsetX = tOffset
                                    }

                                    offsetX = offsetMaxX.toPx()
                                }
                            } else {
                                // Log.e("onDragEnd", "max > standardX")
                                animJob = CoroutineScope(Dispatchers.IO).launch {
                                    val distancePerCnt = (offsetMaxX.toPx() - standardX.toPx())/animMoveCnt
                                    var tOffset: Float

                                    for (i in 0 .. animMoveCnt) {
                                        delay(animDelay)
                                        tOffset = offsetX - distancePerCnt
                                        if (tOffset < standardX.toPx()) tOffset = standardX.toPx()
                                        offsetX = tOffset
                                    }

                                    offsetX = standardX.toPx()
                                }
                            }
                        }
                    }
                )
            }
        ) {
            content()
        }
    }
}

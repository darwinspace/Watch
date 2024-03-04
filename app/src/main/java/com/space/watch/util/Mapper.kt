package com.space.watch.util

import android.content.Context
import android.net.Uri
import com.space.watch.domain.model.Image
import com.space.watch.domain.model.Video

fun Uri?.toVideo(context: Context): Video? {
    return this?.let {
        Video(
            content = it,
            size = context.getVideoSize(it)
        )
    }
}

fun Uri?.toImage(context: Context): Image? {
    return this?.let {
        Image(
            content = it,
            size = context.getImageSize(it)
        )
    }
}


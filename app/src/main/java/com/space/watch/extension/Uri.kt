package com.space.watch.extension

import android.content.Context
import android.net.Uri
import com.space.watch.domain.model.Image
import com.space.watch.domain.model.Video

fun Uri?.toVideo(context: Context): Video? {
    return this?.let {
        Video(
            content = it,
            size = context.getVideoSize(it),
            duration = context.getVideoDuration(it)
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

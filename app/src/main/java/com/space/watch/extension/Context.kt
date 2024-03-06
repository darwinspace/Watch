package com.space.watch.extension

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.net.Uri
import com.space.watch.domain.model.Size

fun Context.getVideoDuration(uri: Uri): Long {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(this, uri)

    val duration = retriever
        .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)!!
        .toLong()

    retriever.release()

    return duration
}

fun Context.getVideoSize(uri: Uri): Size {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(this, uri)

    val width = retriever
        .extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH)!!
        .toInt()

    val height = retriever
        .extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT)!!
        .toInt()

    retriever.release()

    return Size(width, height)
}

fun Context.getImageSize(uri: Uri): Size {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    val inputStream = contentResolver.openInputStream(uri)
    BitmapFactory.decodeStream(inputStream, null, options)
    inputStream?.close()
    return Size(options.outWidth, options.outHeight)
}

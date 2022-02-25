package com.shapes.watch.common

import android.os.Bundle
import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.encode

fun Bundle.getEncodedString(key: String): String? {
    return getString(key)?.decodeBase64()?.string(Charsets.UTF_8)
}

fun String.encode():String {
    return this.encode().base64Url()
}

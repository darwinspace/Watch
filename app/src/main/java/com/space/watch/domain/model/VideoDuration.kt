package com.space.watch.domain.model

data class VideoDuration(val hours: Long, val minutes: Int, val seconds: Int){
    override fun toString(): String {
        return buildString {
            if (hours > 0) append("$hours:")
            append("$minutes:$seconds")
        }
    }
}

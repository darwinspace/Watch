package com.space.watch.extension

import com.space.watch.domain.model.VideoDuration
import kotlin.time.Duration
fun Duration.toVideoDuration(): VideoDuration {
    return toComponents { hours, minutes, seconds, _ ->
        VideoDuration(hours, minutes, seconds)
    }
}

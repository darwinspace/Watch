package com.shapes.watch.data.repository

import com.shapes.watch.data.remote.dto.HomeContentDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.domain.repository.HomeRepository

class FakeHomeRepository : HomeRepository {
    override suspend fun getContent(): HomeContentDto {
        val videos = (1..4).map {
            VideoDto(
                videoId = "$it",
                title = "Video $it",
                description = "Description of the video number $it",
                contentUrl = "$it",
                thumbnailUrl = "https://cdn.dribbble.com/users/10663463/screenshots/17503028/media/236598f749d6dcc361152b17586b0a2b.jpg",
                creatorId = "${it * 24}",
                creatorName = "Creator $it",
                creatorDescription = "Description of the creator number$it",
                creatorPhotoUrl = "https://cdn.dribbble.com/users/6487750/avatars/normal/af90510604209228262f2a8e592d9798.png",
                creatorCoverUrl = ""
            )
        }

        return HomeContentDto(videos)
    }
}

package com.space.watch.presentation.component

import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Pause
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.compose.PlayerSurface
import androidx.media3.ui.compose.state.rememberPlayPauseButtonState

@Composable
fun VideoPlayer(
	modifier: Modifier = Modifier,
	player: Player
) {
	Box {
		PlayerSurface(
			modifier = modifier,
			player = player
		)

		Row(
			modifier = Modifier
				.align(Alignment.BottomCenter)
				.fillMaxWidth()
				.padding(16.dp)
		) {
			PlayPauseButton(
				modifier = Modifier.size(48.dp),
				player = player
			)
		}
	}
}

@OptIn(UnstableApi::class)
@Composable
fun PlayPauseButton(
	modifier: Modifier = Modifier,
	player: Player
) {
	val state = rememberPlayPauseButtonState(player)

	OutlinedIconButton(
		modifier = modifier,
		enabled = state.isEnabled,
		colors = IconButtonDefaults.outlinedIconButtonColors(
			containerColor = MaterialTheme.colorScheme.surface,
			contentColor = MaterialTheme.colorScheme.onSurface
		),
		border = BorderStroke(
			width = 2.dp,
			color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
		),
		onClick = state::onClick
	) {
		Icon(
			imageVector = if (state.showPlay) Icons.Outlined.PlayArrow else Icons.Outlined.Pause,
			contentDescription = null
		)
	}
}


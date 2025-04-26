package ru.evgenykuzakov.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import ru.evgenykuzakov.resources.R
import ru.evgenykuzakov.ui.theme.Glass

@Composable
fun Onboarding(
    continueButtonListener: () -> Unit,
) {
    val hazeState = remember { HazeState() }
    val tagNames = LocalContext.current.resources.getStringArray(R.array.onboarding_content)

    val tags = tagNames.mapIndexed { index, tagName ->
        when (index) {
            1 -> Triple(tagName, -15f, 12)
            8 -> Triple(tagName, 15f, -12)
            14 -> Triple(tagName, -15f, -12)
            else -> Triple(tagName, 0f, 0)
        }
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.padding(top = 100.dp))
        Text(
            text = stringResource(R.string.onboarding_heading),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(Modifier.padding(top = 32.dp))
        TagGrid(tags = tags.subList(0, 3), boxHeight = 60, zIndex = 1f, hazeState = hazeState)
        Spacer(Modifier.padding(top = 8.dp))
        TagGrid(tags = tags.subList(3, 6), boxHeight = 52, zIndex = 2f, hazeState = hazeState)
        Spacer(Modifier.padding(top = 8.dp))
        TagGrid(tags = tags.subList(6, 9), boxHeight = 60, zIndex = 1f, hazeState = hazeState)
        Spacer(Modifier.padding(top = 8.dp))
        TagGrid(tags = tags.subList(9, 13), boxHeight = 52, zIndex = 2f, hazeState = hazeState)
        Spacer(Modifier.padding(top = 8.dp))
        TagGrid(tags = tags.subList(13, 17), boxHeight = 60, zIndex = 1f, hazeState = hazeState)
        Spacer(Modifier.weight(1f))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            onClick = {
                continueButtonListener()
            },
        ) {
            Text(
                modifier = Modifier.padding(vertical = 10.dp),
                text = stringResource(R.string.continue_str),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}


@OptIn(ExperimentalHazeMaterialsApi::class)
@Composable
fun TagItem(
    text: String,
    isHighlighted: Boolean = false,
    angle: Float = 0f,
    boxHeight: Int,
    offset: Int,
    hazeState: HazeState
) {
    if (isHighlighted) {
        Box(
            modifier = Modifier
                .height(boxHeight.dp)
                .offset(y = offset.dp)
                .hazeSource(hazeState)
                .graphicsLayer {
                    rotationZ = angle
                }
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(100.dp))


        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(100.dp))
                .hazeEffect(hazeState, HazeMaterials.ultraThin(Glass))
                .height(boxHeight.dp)


        ) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 24.dp)
            )
        }
    }


}


@Composable
fun TagGrid(
    tags: List<Triple<String, Float, Int>>,
    boxHeight: Int,
    zIndex: Float,
    hazeState: HazeState,
) {
    Row(
        modifier = Modifier
            .zIndex(zIndex)
            .horizontalScroll(rememberScrollState())
    ) {
        tags.forEachIndexed { index, (tag, angle, offset) ->
            TagItem(
                offset = offset,
                text = tag,
                isHighlighted = angle != 0f,
                angle = angle,
                boxHeight = boxHeight,
                hazeState = hazeState
            )
            if (index != tags.size - 1) Spacer(Modifier.padding(end = 4.dp))
        }
    }
}



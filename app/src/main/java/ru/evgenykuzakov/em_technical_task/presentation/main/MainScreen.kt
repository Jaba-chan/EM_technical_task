package ru.evgenykuzakov.em_technical_task.presentation.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeDefaults
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import ru.evgenykuzakov.em_technical_task.R
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.ui.theme.SmoothGlass
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel,
    paddingValues: PaddingValues
) {
    val filterAdded = rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(paddingValues = paddingValues)

    ) {
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = viewModel.searchText.value,
                onValueChange = { viewModel.onEmailTextChanged(it) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_cources),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer
                ),
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Transparent)
                    .height(56.dp)
                    .clip(RoundedCornerShape(100.dp))
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = { filterAdded.value = !filterAdded.value },
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(56.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_filter),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .clickable { }
        ) {
            Text(
                text = stringResource(R.string.sort_by_adding_data),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
            Icon(
                painter = painterResource(R.drawable.ic_arrow_down_up),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable { }
            )
        }

        val state = viewModel.state
        when (state.value) {
            MainScreenViewModel.CoursesState.Empty -> ShowStateMessage(stringResource(R.string.courses_are_emtry))
            is MainScreenViewModel.CoursesState.Error -> ShowStateMessage(stringResource(R.string.somthing_wrong_error))
            MainScreenViewModel.CoursesState.Loading -> ProgressBar()
            is MainScreenViewModel.CoursesState.Success -> {
                ShowCourses(
                    (state.value as MainScreenViewModel.CoursesState.Success).data
                        .let { if (filterAdded.value) it.sortedBy { LocalDate.parse(it.startDate) } else it}
                        .map { it.copy(startDate = formatDate(it.startDate)) },
                    onFavoriteClick = { viewModel.onFavoriteButtonPressed(it) })
            }
        }
    }
}


@Composable
fun CourseCard(
    course: Course,
    onFavoriteClick: (Int) -> Unit
) {
    val hazeState = remember { HazeState() }
    val hazeStyleEffect = HazeStyle(
        backgroundColor = SmoothGlass,
        tints = listOf(HazeTint(SmoothGlass)),
        blurRadius = 16.dp,
        noiseFactor = HazeDefaults.noiseFactor
    )
    val poster = when (course.id) {
        100 -> painterResource(R.drawable.java_developer)
        101 -> painterResource(R.drawable.generalist_3d)
        102 -> painterResource(R.drawable.python_advanced)
        103 -> painterResource(R.drawable.java_developer)
        104 -> painterResource(R.drawable.generalist_3d)
        else -> {painterResource(R.drawable.python_advanced)}
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Box {
                Image(
                    painter = poster,
                    contentDescription = null,
                    modifier = Modifier
                        .hazeSource(hazeState)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(100.dp))
                        .background(color = SmoothGlass)
                        .wrapContentSize()
                        .clickable { onFavoriteClick(course.id) }
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(6.dp)
                            .size(16.dp)
                            .align(Alignment.Center),
                        painter = if (course.hasLike) painterResource(R.drawable.ic_bookmark_fill) else painterResource(
                            R.drawable.ic_bookmark
                        ),
                        contentDescription = null,
                        tint = if (course.hasLike) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                    )
                }


                Row(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 8.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .hazeEffect(hazeState, style = hazeStyleEffect)
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(12.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = course.rate,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelSmall.copy(lineHeight = 14.sp)
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .hazeEffect(hazeState, style = hazeStyleEffect)
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = course.startDate,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.labelSmall.copy(lineHeight = 14.sp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Column(modifier = Modifier.padding(start = 16.dp, end = 12.dp)) {
                Text(
                    text = course.title,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = course.text,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.labelSmall.copy(
                        lineHeight = 16.sp,
                        fontWeight = FontWeight.W400
                    ),
                    modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
                )
                Spacer(Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Text(
                        text = String.format(stringResource(R.string.price_format), course.price),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium
                    )
                    Row(
                        modifier = Modifier
                            .clickable { },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.read_more),
                            color = MaterialTheme.colorScheme.primary,
                            style = MaterialTheme.typography.labelSmall
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_right),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun ShowCourses(
    courses: List<Course>,
    onFavoriteClick: (Int) -> Unit

) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        items(courses.size) { course ->
            CourseCard(courses[course], onFavoriteClick = onFavoriteClick)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ColumnScope.ProgressBar() {
    Spacer(Modifier.weight(1f))
    CircularProgressIndicator(
        modifier = Modifier
            .width(44.dp)
            .align(Alignment.CenterHorizontally),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
    Spacer(Modifier.weight(1f))
}

@Composable
fun ColumnScope.ShowStateMessage(text: String) {
    Spacer(Modifier.weight(1f))
    Text(
        modifier = Modifier
            .padding(horizontal = 32.dp),
        text = text,
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        textAlign = TextAlign.Center
    )
    Spacer(Modifier.weight(1f))
}

private fun formatDate(dateString: String): String {
    val parsedDate = LocalDate.parse(dateString)
    val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
    return parsedDate.format(formatter)
        .replaceFirstChar { it.titlecase(Locale("ru")) }
}
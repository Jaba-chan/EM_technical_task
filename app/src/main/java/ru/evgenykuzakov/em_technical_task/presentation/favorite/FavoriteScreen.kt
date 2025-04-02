package ru.evgenykuzakov.em_technical_task.presentation.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.evgenykuzakov.em_technical_task.R
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.presentation.main.CourseCard
import ru.evgenykuzakov.em_technical_task.presentation.main.ProgressBar
import ru.evgenykuzakov.em_technical_task.presentation.main.ShowCourses
import ru.evgenykuzakov.em_technical_task.presentation.main.ShowStateMessage

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues,
    viewModel: FavoriteScreenViewModel
) {
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    val state = viewModel.state
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(paddingValues = paddingValues)

    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(16.dp),
            text = stringResource(R.string.favorite),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        when (state.value) {
            FavoriteScreenViewModel.FavoriteCoursesState.Empty -> ShowStateMessage(stringResource(R.string.courses_are_emtry))
            is FavoriteScreenViewModel.FavoriteCoursesState.Error -> ShowStateMessage(
                stringResource(
                    R.string.somthing_wrong_error
                )
            )

            FavoriteScreenViewModel.FavoriteCoursesState.Loading -> ProgressBar()
            is FavoriteScreenViewModel.FavoriteCoursesState.Success -> {
                ShowCourses(
                    (state.value as FavoriteScreenViewModel.FavoriteCoursesState.Success).data
                ) {}
            }
        }

    }
}


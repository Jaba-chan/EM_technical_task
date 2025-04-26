package ru.evgenykuzakov.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.evgenykuzakov.model.Course

//@Composable
//fun ShowCourses(
//    courses: List<Course>,
//    onFavoriteClick: (Int) -> Unit
//
//) {
//    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
//        items(courses.size) { course ->
//            CourseCard(courses[course], onFavoriteClick = onFavoriteClick)
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }
//}
package ru.evgenykuzakov.em_technical_task.data.remote.model

import kotlinx.serialization.Serializable

data class CourseResponse(
    val courses: List<CourseDto>
)

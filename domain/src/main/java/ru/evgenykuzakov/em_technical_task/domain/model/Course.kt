package ru.evgenykuzakov.em_technical_task.domain.model

data class Course(
val hasLike: Boolean,
val id: Int,
val price: String,
val publishDate: String,
val rate: String,
val startDate: String,
val text: String,
val title: String
)
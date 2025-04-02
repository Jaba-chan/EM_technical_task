package ru.evgenykuzakov.em_technical_task.data.mapper

import ru.evgenykuzakov.em_technical_task.data.local.model.CourseEntity
import ru.evgenykuzakov.em_technical_task.data.remote.model.CourseDto
import ru.evgenykuzakov.em_technical_task.domain.model.Course

fun CourseDto.toDomain() = Course(
    hasLike = hasLike,
    id = id,
    price = price,
    publishDate = publishDate,
    rate = rate,
    startDate = startDate,
    text = text,
    title = title,
)

fun CourseEntity.toDomain() = Course(
    hasLike = hasLike,
    id = id,
    price = price,
    publishDate = publishDate,
    rate = rate,
    startDate = startDate,
    text = text,
    title = title,
)


fun Course.toEntity() = CourseEntity(
    hasLike = hasLike,
    id = id,
    price = price,
    publishDate = publishDate,
    rate = rate,
    startDate = startDate,
    text = text,
    title = title,
)
package ru.evgenykuzakov.data.mapper

import ru.evgenykuzakov.database.model.CourseEntity
import ru.evgenykuzakov.model.Course
import ru.evgenykuzakov.network.model.CourseDto


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
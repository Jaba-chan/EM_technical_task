package ru.evgenykuzakov.em_technical_task.data.remote

import retrofit2.http.GET
import retrofit2.http.Url
import ru.evgenykuzakov.em_technical_task.data.remote.model.CourseResponse


interface Api {
    @GET
    suspend fun getCourses(@Url url: String): CourseResponse

}
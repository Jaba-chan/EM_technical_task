package ru.evgenykuzakov.network.retrofit

import retrofit2.http.GET
import retrofit2.http.Url
import ru.evgenykuzakov.network.model.CourseResponse


interface Api {
    @GET
    suspend fun getCourses(@Url url: String): CourseResponse

}
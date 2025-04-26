package ru.evgenykuzakov.em_technical_task.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.evgenykuzakov.data.repository.AppLaunchRepositoryImpl
import ru.evgenykuzakov.data.repository.AppRemoteRepositoryImpl
import ru.evgenykuzakov.data.repository.FavoriteCoursesRepositoryImpl
import ru.evgenykuzakov.database.FavoriteCoursesDatabase
import ru.evgenykuzakov.domain.repository.AppLaunchRepository
import ru.evgenykuzakov.domain.repository.AppRepository
import ru.evgenykuzakov.domain.repository.FavoriteCoursesRepository
import ru.evgenykuzakov.domain.usecase.AddFavoriteCourseUseCase
import ru.evgenykuzakov.domain.usecase.CheckFirstLaunchUseCase
import ru.evgenykuzakov.domain.usecase.SetFirstLaunchCompleteUseCase
import ru.evgenykuzakov.domain.usecase.ShowCourserUseCase
import ru.evgenykuzakov.domain.usecase.ShowFavoriteCoursesUseCase
import ru.evgenykuzakov.network.retrofit.Api


val appModule = module {
    single<Api> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
    single {
        Room.databaseBuilder(
            get<Application>(),
           FavoriteCoursesDatabase::class.java,
            "app_db"
        ).build()
    }
    single { AppLaunchRepositoryImpl(androidContext()) }.bind(AppLaunchRepository::class)
    single { AppRemoteRepositoryImpl(get()) }.bind(AppRepository::class)
    single { FavoriteCoursesRepositoryImpl(get()) }.bind(FavoriteCoursesRepository::class)

    single { CheckFirstLaunchUseCase(get()) }
    single { ShowCourserUseCase(get()) }
    single { SetFirstLaunchCompleteUseCase(get()) }
    single { ShowFavoriteCoursesUseCase(get()) }
    single { AddFavoriteCourseUseCase(get()) }

}
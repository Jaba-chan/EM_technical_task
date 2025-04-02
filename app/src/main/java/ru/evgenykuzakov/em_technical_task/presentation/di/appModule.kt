package ru.evgenykuzakov.em_technical_task.presentation.di

import android.app.Application
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.evgenykuzakov.em_technical_task.data.local.room_db.FavoriteCoursesDatabase
import ru.evgenykuzakov.em_technical_task.data.remote.Api
import ru.evgenykuzakov.em_technical_task.data.remote.repository.AppLaunchRepositoryImpl
import ru.evgenykuzakov.em_technical_task.data.remote.repository.AppRemoteRepositoryImpl
import ru.evgenykuzakov.em_technical_task.data.remote.repository.FavoriteCoursesRepositoryImpl
import ru.evgenykuzakov.em_technical_task.data.remote.repository.StorageRepositoryImpl
import ru.evgenykuzakov.em_technical_task.domain.repository.AppLaunchRepository
import ru.evgenykuzakov.em_technical_task.domain.repository.AppRepository
import ru.evgenykuzakov.em_technical_task.domain.repository.FavoriteCoursesRepository
import ru.evgenykuzakov.em_technical_task.domain.repository.StorageRepository
import ru.evgenykuzakov.em_technical_task.domain.usecase.AddFavoriteCourseUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.CheckFirstLaunchUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.CheckSignInStateUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.SetFirstLaunchCompleteUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.SetSignInStateUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.ShowCourserUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.ShowFavoriteCoursesUseCase
import ru.evgenykuzakov.em_technical_task.presentation.MainActivityViewModel
import ru.evgenykuzakov.em_technical_task.presentation.favorite.FavoriteScreenViewModel
import ru.evgenykuzakov.em_technical_task.presentation.main.MainScreenViewModel
import ru.evgenykuzakov.em_technical_task.presentation.sign_in.SignInViewModel


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
    single { StorageRepositoryImpl(androidContext()) }.bind(StorageRepository::class)
    single { AppRemoteRepositoryImpl(get()) }.bind(AppRepository::class)
    single { FavoriteCoursesRepositoryImpl(get()) }.bind(FavoriteCoursesRepository::class)

    single { CheckFirstLaunchUseCase(get()) }
    single { ShowCourserUseCase(get()) }
    single { SetFirstLaunchCompleteUseCase(get()) }
    single { CheckSignInStateUseCase(get()) }
    single { SetSignInStateUseCase(get()) }
    single { ShowFavoriteCoursesUseCase(get()) }
    single { AddFavoriteCourseUseCase(get()) }

    viewModel { MainActivityViewModel(get(), get(), get(), get()) }
    viewModel { SignInViewModel() }
    viewModel { MainScreenViewModel(get(), get()) }
    viewModel { FavoriteScreenViewModel(get())}
}
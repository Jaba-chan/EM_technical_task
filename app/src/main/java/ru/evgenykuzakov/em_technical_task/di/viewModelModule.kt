package ru.evgenykuzakov.em_technical_task.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.evgenykuzakov.em_technical_task.MainActivityViewModel

val viewModelModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { ru.evgenykuzakov.login.SignInViewModel() }
    viewModel { ru.evgenykuzakov.courses.MainScreenViewModel(get(), get()) }
    viewModel { ru.evgenykuzakov.favorite.FavoriteScreenViewModel(get()) }
}
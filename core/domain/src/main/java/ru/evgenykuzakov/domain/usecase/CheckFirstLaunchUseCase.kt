package ru.evgenykuzakov.domain.usecase

import ru.evgenykuzakov.domain.repository.AppLaunchRepository

class CheckFirstLaunchUseCase(private val repository: AppLaunchRepository) {
    operator fun invoke(): Boolean = repository.isFirstLaunch()
}

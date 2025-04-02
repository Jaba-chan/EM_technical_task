package ru.evgenykuzakov.em_technical_task.domain.usecase

import ru.evgenykuzakov.em_technical_task.domain.repository.AppLaunchRepository

class CheckFirstLaunchUseCase(private val repository: AppLaunchRepository) {
    operator fun invoke(): Boolean = repository.isFirstLaunch()
}

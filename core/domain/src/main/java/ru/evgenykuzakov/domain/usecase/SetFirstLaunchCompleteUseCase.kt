package ru.evgenykuzakov.domain.usecase

import ru.evgenykuzakov.domain.repository.AppLaunchRepository

class SetFirstLaunchCompleteUseCase(private val repository: AppLaunchRepository) {
    operator fun invoke() = repository.setFirstLaunchComplete()
}
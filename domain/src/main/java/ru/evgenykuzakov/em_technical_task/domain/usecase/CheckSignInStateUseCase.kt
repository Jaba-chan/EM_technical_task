package ru.evgenykuzakov.em_technical_task.domain.usecase

import ru.evgenykuzakov.em_technical_task.domain.repository.StorageRepository

class CheckSignInStateUseCase(private val repository: StorageRepository) {
    operator fun invoke(): Boolean = repository.getSignedState()
}
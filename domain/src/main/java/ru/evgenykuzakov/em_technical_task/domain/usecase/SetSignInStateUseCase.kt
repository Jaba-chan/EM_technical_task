package ru.evgenykuzakov.em_technical_task.domain.usecase

import ru.evgenykuzakov.em_technical_task.domain.repository.StorageRepository

class SetSignInStateUseCase (private val repository: StorageRepository) {
    operator fun invoke() = repository.setSignedState(true)
}
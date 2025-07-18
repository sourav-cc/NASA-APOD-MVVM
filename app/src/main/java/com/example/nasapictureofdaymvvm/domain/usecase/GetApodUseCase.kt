package com.example.nasapictureofdaymvvm.domain.usecase

import com.example.nasapictureofdaymvvm.domain.NasaRepository
import javax.inject.Inject

class GetApodUseCase @Inject constructor(private val repository: NasaRepository) {
    operator fun invoke() = repository.getApod()
}
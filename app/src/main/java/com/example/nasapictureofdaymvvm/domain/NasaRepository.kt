package com.example.nasapictureofdaymvvm.domain

import com.example.nasapictureofdaymvvm.data.ApodResponse
import kotlinx.coroutines.flow.Flow

interface NasaRepository {
    fun getApod(): Flow<Result<ApodResponse>>
}
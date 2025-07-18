package com.example.nasapictureofdaymvvm.data.repository

import com.example.nasapictureofdaymvvm.data.ApodResponse
import com.example.nasapictureofdaymvvm.data.NasaApiService
import com.example.nasapictureofdaymvvm.domain.NasaRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NasaRepositoryImpl @Inject constructor(
    private val apiService: NasaApiService,
    private val apiKey: String,
    private val ioDispatcher: CoroutineDispatcher
) : NasaRepository {

    override fun getApod(): Flow<Result<ApodResponse>> = flow {
        try {
            val response = apiService.getApod(apiKey)
            emit(
                Result.success(
                    ApodResponse(
                        date = response.date,
                        explanation = response.explanation,
                        mediaType = response.mediaType,
                        title = response.title,
                        url = response.url,
                        hdUrl = response.hdUrl ?: "",
                        copyright = response.copyright ?: "No copyright information available"
                    )
                )
            )
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }.flowOn(ioDispatcher).catch { exception -> emit(Result.failure(exception)) }
}
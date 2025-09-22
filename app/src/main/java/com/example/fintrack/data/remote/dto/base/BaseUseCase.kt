package com.example.fintrack.data.remote.dto.base

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}
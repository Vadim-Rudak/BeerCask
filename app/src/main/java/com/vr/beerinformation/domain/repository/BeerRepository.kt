package com.vr.beerinformation.domain.repository

import com.vr.beerinformation.data.model.Beer
import retrofit2.Response

interface BeerRepository {
    suspend fun GetAllBeer(): Response<List<Beer>>
}
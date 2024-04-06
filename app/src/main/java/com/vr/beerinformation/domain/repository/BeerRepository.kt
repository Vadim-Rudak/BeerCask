package com.vr.beerinformation.domain.repository

import com.vr.beerinformation.data.model.Beer
import kotlinx.coroutines.flow.Flow

interface BeerRepository {
    fun getAllBeer(): Flow<List<Beer>>
}
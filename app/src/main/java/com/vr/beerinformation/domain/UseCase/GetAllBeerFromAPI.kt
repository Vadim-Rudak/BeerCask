package com.vr.beerinformation.domain.UseCase

import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.repository.BeerRepository
import kotlinx.coroutines.flow.Flow

class GetAllBeerFromAPI(private val beerRepository: BeerRepository) {
    fun execute(): Flow<List<Beer>>{
        return beerRepository.getAllBeer()
    }
}
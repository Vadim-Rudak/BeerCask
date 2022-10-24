package com.vr.beerinformation.domain.UseCase

import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.repository.BeerRepository
import retrofit2.Response

class GetAllBeerFromAPI(private val beerRepository: BeerRepository) {
    suspend fun execute(): Response<List<Beer>>{
        return beerRepository.GetAllBeer()
    }
}
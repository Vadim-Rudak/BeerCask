package com.vr.beerinformation.domain.UseCase

import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.repository.BDRepository

class GetAllBeerBD(private val bdRepository: BDRepository) {
    fun execute(): ArrayList<Beer>{
        return bdRepository.getAllBeer()
    }
}
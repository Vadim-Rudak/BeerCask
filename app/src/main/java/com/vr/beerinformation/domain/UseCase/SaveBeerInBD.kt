package com.vr.beerinformation.domain.UseCase

import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.repository.BDRepository

class SaveBeerInBD(private val bdRepository: BDRepository) {
    fun execute(Beer: List<Beer>){
        bdRepository.saveBeerInDB(Beer)
    }
}
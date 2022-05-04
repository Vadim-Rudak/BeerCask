package com.vr.beerinformation.domain.repository

import com.vr.beerinformation.data.model.Beer

interface BDRepository {
    fun getAllBeer(): ArrayList<Beer>
    fun saveBeerInDB(Beer: List<Beer>)
}
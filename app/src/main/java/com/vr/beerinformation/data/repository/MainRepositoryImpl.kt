package com.vr.beerinformation.data.repository

import com.vr.beerinformation.data.api.NetworkService
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.repository.BeerRepository
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainRepositoryImpl constructor(private val networkService: NetworkService) : BeerRepository{

    override suspend fun GetAllBeer() = networkService.getAllInfoBeer()

}
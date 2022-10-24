package com.vr.beerinformation.di

import com.vr.beerinformation.data.api.NetworkService
import com.vr.beerinformation.data.repository.BDHelperImpl
import com.vr.beerinformation.data.repository.MainRepositoryImpl
import com.vr.beerinformation.domain.UseCase.GetAllBeerBD
import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.domain.UseCase.SaveBeerInBD
import com.vr.beerinformation.domain.repository.BDRepository
import com.vr.beerinformation.domain.repository.BeerRepository
import org.koin.dsl.module

val dataModule = module {

    single<BeerRepository>{
        MainRepositoryImpl(NetworkService.getInstance())
    }

    single<BDRepository> {
        BDHelperImpl(context = get())
    }

}
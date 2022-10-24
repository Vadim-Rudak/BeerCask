package com.vr.beerinformation.di

import com.vr.beerinformation.domain.UseCase.GetAllBeerBD
import com.vr.beerinformation.domain.UseCase.GetAllBeerFromAPI
import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.domain.UseCase.SaveBeerInBD
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllBeerBD> {
        GetAllBeerBD(bdRepository = get())
    }

    factory<SaveBeerInBD> {
        SaveBeerInBD(bdRepository = get())
    }

    factory<GetAllBeerFromAPI> {
        GetAllBeerFromAPI(beerRepository = get())
    }

    factory<InternetConnection> {
        InternetConnection(context = get())
    }
}
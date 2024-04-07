package com.vr.beerinformation.di

import com.vr.beerinformation.domain.UseCase.*
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
package com.vr.beerinformation.di

import com.vr.beerinformation.data.repository.BDHelperImpl
import com.vr.beerinformation.data.repository.FirebaseRepositoryImpl
import com.vr.beerinformation.domain.repository.BDRepository
import com.vr.beerinformation.domain.repository.BeerRepository
import org.koin.dsl.module

val dataModule = module {

    single<BeerRepository>{
        FirebaseRepositoryImpl()
    }

    single<BDRepository> {
        BDHelperImpl(context = get())
    }

}
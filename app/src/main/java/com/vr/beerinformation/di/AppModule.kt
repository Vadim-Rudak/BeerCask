package com.vr.beerinformation.di

import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainViewModel>{
        MainViewModel(
            getAllBeerFromAPI = get(),
            internetConnection = get(),
            getAllBeerBD = get(),
            saveBeerInBD = get()
        )
    }

}
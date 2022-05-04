package com.vr.beerinformation.ui.base

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vr.beerinformation.data.repository.MainRepositoryImpl
import com.vr.beerinformation.domain.UseCase.GetAllBeerBD
import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.domain.UseCase.SaveBeerInBD
import com.vr.beerinformation.domain.repository.BDRepository
import com.vr.beerinformation.domain.repository.BeerRepository
import com.vr.beerinformation.ui.main.viewmodel.MainViewModel

class MyViewModelFactory constructor(private val beerRepository: BeerRepository,private val bdRepository: BDRepository,private val context: Context): ViewModelProvider.Factory {

    val internetConnection by lazy(LazyThreadSafetyMode.NONE) { InternetConnection(context) }
    val getAllBeerBD by lazy(LazyThreadSafetyMode.NONE) { GetAllBeerBD(bdRepository) }
    val saveBeerInBD by lazy(LazyThreadSafetyMode.NONE) { SaveBeerInBD(bdRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.beerRepository,internetConnection,getAllBeerBD,saveBeerInBD) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
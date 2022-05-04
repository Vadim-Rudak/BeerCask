package com.vr.beerinformation.ui.main.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.data.repository.MainRepositoryImpl
import com.vr.beerinformation.domain.UseCase.GetAllBeerBD
import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.domain.UseCase.SaveBeerInBD
import com.vr.beerinformation.domain.repository.BeerRepository
import com.vr.beerinformation.ui.main.adapter.BtnBeerAdapter
import com.vr.beerinformation.ui.main.view.FragmentAllBeer
import kotlinx.coroutines.*

class MainViewModel(private val beerRepository: BeerRepository,
                    private val internetConnection: InternetConnection,
                    private val getAllBeerBD: GetAllBeerBD,
                    private val saveBeerInBD: SaveBeerInBD) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    var beerList = MutableLiveData<List<Beer>>()
    var adapter = BtnBeerAdapter()
    var internet = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null

    fun getAllBeer(){
        job = CoroutineScope(Dispatchers.IO).launch {
            loading.postValue(true)
            val response = beerRepository.GetAllBeer()
            Log.d("HHH", response.body().toString())
            print(response.body().toString())
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    beerList.postValue(response.body())
                    saveBeerInBD.execute(response.body()!!)
                    loading.value = false
                }else{
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    fun getAllBeerBD(){
        beerList.postValue(getAllBeerBD.execute())
    }

    fun ChekInternetConnection(){
        internet.value = internetConnection.UseInternet()
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
package com.vr.beerinformation.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.UseCase.GetAllBeerBD
import com.vr.beerinformation.domain.UseCase.GetAllBeerFromAPI
import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.domain.UseCase.SaveBeerInBD
import com.vr.beerinformation.ui.main.adapter.BtnBeerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getAllBeerFromAPI: GetAllBeerFromAPI,
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

            getAllBeerFromAPI.execute().collectIndexed { _, value ->
                beerList.postValue(value)
                saveBeerInBD.execute(value)
                withContext(Dispatchers.Main){
                    loading.value = false
                }
            }
        }
    }

    fun getAllBeerBD(){
        beerList.postValue(getAllBeerBD.execute())
    }

    fun checkInternetConnection(){
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
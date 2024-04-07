package com.vr.beerinformation.ui.main.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vr.beerinformation.data.model.Beer
import com.vr.beerinformation.domain.UseCase.GetAllBeerBD
import com.vr.beerinformation.domain.UseCase.GetAllBeerFromAPI
import com.vr.beerinformation.domain.UseCase.InternetConnection
import com.vr.beerinformation.domain.UseCase.SaveBeerInBD
import com.vr.beerinformation.ui.main.adapter.BtnBeerAdapter
import com.vr.beerinformation.ui.main.view.FragmentAllBeer
import com.vr.beerinformation.ui.main.view.FragmentInfoOneBeer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class MainViewModel(private val getAllBeerFromAPI: GetAllBeerFromAPI,
                    private val internetConnection: InternetConnection,
                    private val getAllBeerBD: GetAllBeerBD,
                    private val saveBeerInBD: SaveBeerInBD) : ViewModel() {

    private var job: Job? = null
    var title = MutableLiveData<String>()
    var selectFragment = MutableLiveData<Fragment>()
    var adapter = BtnBeerAdapter()
    val loading = MutableLiveData<Boolean>()

    init {
        getAllBeer()
        selectBeer()
    }

    private fun selectBeer(){
        adapter.chekInternet = internetConnection.UseInternet()
        adapter.setListener(object : BtnBeerAdapter.Listener {
            override fun clicked(beer: Beer) {
                title.value = beer.name
                selectFragment.postValue(FragmentInfoOneBeer(internetConnection.UseInternet(),beer))
            }
        })
    }

    private fun getAllBeer(){
        job = CoroutineScope(Dispatchers.IO).launch {
            if (internetConnection.UseInternet()){
                loading.postValue(true)
                getAllBeerFromAPI.execute().collectIndexed { _, value ->
                    saveBeerInBD.execute(value)
                    seeListBeer(value)
                }
            }else{
                seeListBeer(getAllBeerBD.execute())
            }
        }
    }

    private fun seeListBeer(listBeer:List<Beer>){
        job = CoroutineScope(Dispatchers.Main).launch {
            adapter.setBeer(listBeer)
            loading.postValue(false)
            selectFragment.postValue(FragmentAllBeer())
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
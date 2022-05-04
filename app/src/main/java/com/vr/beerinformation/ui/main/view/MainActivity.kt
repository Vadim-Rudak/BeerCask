package com.vr.beerinformation.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vr.beerinformation.R
import com.vr.beerinformation.data.api.NetworkService
import com.vr.beerinformation.data.repository.MainRepositoryImpl
import com.vr.beerinformation.data.repository.BDHelperImpl
import com.vr.beerinformation.ui.base.MyViewModelFactory
import com.vr.beerinformation.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    lateinit var viewModel: MainViewModel
    lateinit var DBHelperImpl : BDHelperImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var progressBar = findViewById<ProgressBar>(R.id.progressDialog)
        var frameLayout = findViewById<FrameLayout>(R.id.fr_place)
        toolbar = findViewById<Toolbar>(R.id.toolbar_first_window)
        setSupportActionBar(toolbar)
        DBHelperImpl = BDHelperImpl(this)

        val retrofitService = NetworkService.getInstance()
        val mainRepository = MainRepositoryImpl(retrofitService)
        val bdHelper = BDHelperImpl(this)

        viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository,bdHelper,this))
            .get(MainViewModel::class.java)

        viewModel.ChekInternetConnection()

        viewModel.beerList.observe(this) {
            viewModel.adapter.setBeer(it)
        }
        viewModel.internet.observe(this){
            if (it){
                viewModel.adapter.ChekInternet = true

                var fragmentAllBeer = FragmentAllBeer(toolbar,viewModel.adapter)
                viewModel.loading.observe(this, Observer {
                    if (it) {
                        progressBar?.visibility = View.VISIBLE
                        frameLayout?.visibility = View.GONE
                    } else {
                        progressBar?.visibility = View.GONE
                        frameLayout?.visibility = View.VISIBLE

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.fr_place, fragmentAllBeer)
                            .commit()
                    }
                })
                viewModel.getAllBeer()

            }else{
                viewModel.adapter.ChekInternet = false

                viewModel.getAllBeerBD()
                progressBar?.visibility = View.GONE
                frameLayout?.visibility = View.VISIBLE
                var fragmentAllBeer = FragmentAllBeer(toolbar,viewModel.adapter)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fr_place, fragmentAllBeer)
                    .commit()
            }
        }
    }
}
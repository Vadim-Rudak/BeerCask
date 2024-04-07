package com.vr.beerinformation.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.vr.beerinformation.R
import com.vr.beerinformation.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progressDialog)
        val frameLayout = findViewById<FrameLayout>(R.id.fr_place)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_first_window)
        setSupportActionBar(toolbar)

        viewModel.title.observe(this){
            toolbar.title = it
        }

        viewModel.selectFragment.observe(this){
            val fragmentManager = supportFragmentManager.beginTransaction()
                .replace(R.id.fr_place, it)
            if (it is FragmentInfoOneBeer){
                fragmentManager.addToBackStack(null)
            }
            fragmentManager.commit()
        }

        viewModel.loading.observe(this){
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
            frameLayout.visibility = if (it) View.GONE else View.VISIBLE
        }
    }
}
package com.vr.beerinformation.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.vr.beerinformation.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash : AppCompatActivity() {

    private var job: Job?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)
        animationView.apply {
            setAnimation("beerLogoAnim.json")
        }.playAnimation()

        job = CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            Intent(this@Splash,MainActivity::class.java).also {
                startActivity(it)
            }
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}
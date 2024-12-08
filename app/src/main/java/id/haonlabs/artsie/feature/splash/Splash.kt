package id.haonlabs.artsie.feature.splash

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.haonlabs.artsie.R
import id.haonlabs.artsie.feature.home.ui.Home

class Splash : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)

    val ivLoading: ImageView = findViewById(R.id.iv_loading)
    Glide.with(this).load(R.drawable.loading).into(ivLoading)

    android.os
        .Handler(Looper.getMainLooper())
        .postDelayed(
            {
              val intent = Intent(this@Splash, Home::class.java)
              startActivity(intent)
              finish()
            },
            3000)
  }
}

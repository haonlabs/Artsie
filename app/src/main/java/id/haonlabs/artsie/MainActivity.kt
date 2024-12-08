package id.haonlabs.artsie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import id.haonlabs.artsie.feature.splash.Splash

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val toSplash = Intent(this@MainActivity, Splash::class.java)
    startActivity(toSplash)
  }
}

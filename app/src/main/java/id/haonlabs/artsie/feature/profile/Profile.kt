package id.haonlabs.artsie.feature.profile

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import id.haonlabs.artsie.R

class Profile : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = "Profile"
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean =
      when (item.itemId) {
        android.R.id.home -> {
          finish()
          true
        }

        else -> super.onOptionsItemSelected(item)
      }
}

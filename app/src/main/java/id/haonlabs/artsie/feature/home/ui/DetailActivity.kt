package id.haonlabs.artsie.feature.home.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import id.haonlabs.artsie.R
import id.haonlabs.artsie.feature.home.model.Art

class DetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_detail)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = "Detail"
    setTheme(R.style.Theme_Artsie)
    val data =
        if (Build.VERSION.SDK_INT >= 33) {
          intent.getParcelableExtra<Art>("DATA", Art::class.java)
        } else {
          @Suppress("DEPRECATION") intent.getParcelableExtra<Art>("DATA")
        }

    Log.d("Detail Data", data.toString())

    val photo: ImageView = findViewById(R.id.iv_detail_photo)
    val title: TextView = findViewById(R.id.tv_title)
    val materials: TextView = findViewById(R.id.tv_materials)
    val dimensions: TextView = findViewById(R.id.tv_dimensions)
    val history: TextView = findViewById(R.id.tv_history)

    if (data != null) {
      photo.setImageResource(data.photo)
      title.text = data.title
      materials.text = data.materials
      dimensions.text = data.dimensions
      history.text = data.history
    }
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

package id.haonlabs.artsie.feature.home.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.haonlabs.artsie.R
import id.haonlabs.artsie.feature.home.model.Art

class DetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_detail)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.title = "Detail"
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
    val btnShare: Button = findViewById(R.id.btn_share)

    if (data != null) {
      photo.setImageResource(data.photo)
      title.text = data.title
      materials.text = data.materials
      dimensions.text = data.dimensions
      history.text = data.history
    }

    btnShare.setOnClickListener {
      val shareIntent =
          Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                """
                        You must watch this Art!
                        Title: ${data?.title}
                        """
                    .trimIndent(),
            ) // Add the text to share
            type = "text/plain" // Specify the type of content
          }

      // Start the sharing activity
      startActivity(Intent.createChooser(shareIntent, "Share with: "))
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

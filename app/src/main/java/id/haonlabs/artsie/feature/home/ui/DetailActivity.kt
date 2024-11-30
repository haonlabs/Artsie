package id.haonlabs.artsie.feature.home.ui

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.haonlabs.artsie.R
import id.haonlabs.artsie.feature.home.model.Art

class DetailActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_detail)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
      val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
      insets
    }

    val data = intent.getParcelableExtra<Art>("DATA")
    Log.d("Detail Data", data?.name.toString())
    val photo: ImageView = findViewById(R.id.iv_detail_photo)
    val name: TextView = findViewById(R.id.tv_name)
    val desc: TextView = findViewById(R.id.tv_desc)
    val artist: TextView = findViewById(R.id.tv_artist)
    photo.setImageResource(data?.photo!!)
    name.text = data.name
    desc.text = data.desc
    artist.text = data.artist
  }
}

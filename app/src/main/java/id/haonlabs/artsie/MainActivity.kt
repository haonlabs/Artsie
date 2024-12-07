package id.haonlabs.artsie

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import id.haonlabs.artsie.feature.home.adapter.LatestArtAdapter
import id.haonlabs.artsie.feature.home.adapter.PopularArtAdapter
import id.haonlabs.artsie.feature.home.model.Art
import id.haonlabs.artsie.feature.home.ui.DetailActivity

class MainActivity : AppCompatActivity() {
  private lateinit var rvLatest: RecyclerView
  private lateinit var rvPopular: RecyclerView
  private val listArt = ArrayList<Art>()

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen()
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    rvPopular = findViewById(R.id.rv_popular)
    rvPopular.setHasFixedSize(true)

    rvLatest = findViewById(R.id.rv_latest)
    rvLatest.setHasFixedSize(true)

    val tvPopular: TextView = findViewById(R.id.tv_popular)

    listArt.addAll(getLatestArt())
    if (applicationContext.resources.configuration.orientation ==
        Configuration.ORIENTATION_LANDSCAPE) {
      tvPopular.visibility = TextView.GONE
      rvPopular.visibility = RecyclerView.GONE
      showRvLatest()
    } else {
      showRvPopular()
      showRvLatest()
    }
  }

  @SuppressLint("Recycle")
  private fun getLatestArt(): ArrayList<Art> {
    val dataTitle = resources.getStringArray(R.array.data_title)
    val dataHistory = resources.getStringArray(R.array.data_history)
    val dataMaterials = resources.getStringArray(R.array.data_materials)
    val dataDimensions = resources.getStringArray(R.array.data_dimensions)
    val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
    val listLatest = ArrayList<Art>()
    for (i in dataTitle.indices) {
      val art =
          Art(
              dataTitle[i],
              dataHistory[i],
              dataMaterials[i],
              dataDimensions[i],
              dataPhoto.getResourceId(i, -1))
      listLatest.add(art)
    }
    return listLatest
  }

  private fun showRvPopular() {
    rvPopular.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    val popularArtAdapter = PopularArtAdapter(listArt.takeLast(4))
    rvPopular.adapter = popularArtAdapter

    popularArtAdapter.setOnItemClickCallback(
        object : PopularArtAdapter.OnItemClickCallback {
          override fun onItemClicked(data: Art) {
            showSelectedArt(data)
            val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
            intentToDetail.putExtra("DATA", data)
            startActivity(intentToDetail)
          }
        })
  }

  private fun showRvLatest() {
    rvLatest.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    val latestArtAdapter = LatestArtAdapter(listArt)
    rvLatest.adapter = latestArtAdapter

    latestArtAdapter.setOnItemClickCallback(
        object : LatestArtAdapter.OnItemClickCallback {
          override fun onItemClicked(data: Art) {
            showSelectedArt(data)
            val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
            intentToDetail.putExtra("DATA", data)
            startActivity(intentToDetail)
          }
        })
  }

  private fun showSelectedArt(art: Art) {
    Toast.makeText(this, "Memilih lukisan ${art.title}", Toast.LENGTH_SHORT).show()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.action_staggered -> {
        rvLatest.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
      }

      R.id.action_list -> {
        rvLatest.layoutManager = LinearLayoutManager(this)
      }
    }
    return super.onOptionsItemSelected(item)
  }
}

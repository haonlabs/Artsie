package id.haonlabs.artsie.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import id.haonlabs.artsie.R
import id.haonlabs.artsie.feature.home.adapter.LatestArtAdapter.OnItemClickCallback
import id.haonlabs.artsie.feature.home.model.Art

class PopularArtAdapter(private val listArt: ArrayList<Art>) :
    RecyclerView.Adapter<PopularArtAdapter.ListArtViewHolder>() {
  private lateinit var onItemClickCallback: OnItemClickCallback

  fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
    this.onItemClickCallback = onItemClickCallback
  }

  class ListArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imgPhoto: ImageView = itemView.findViewById(R.id.iv_card_item_photo)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListArtViewHolder {
    val view: View =
        LayoutInflater.from(parent.context).inflate(R.layout.linear_card_item, parent, false)
    return ListArtViewHolder(view)
  }

  override fun onBindViewHolder(holder: ListArtViewHolder, position: Int) {
    val (name, desc, artist, photo) = listArt[position]
    holder.imgPhoto.setImageResource(photo)
    holder.itemView.setOnClickListener {
      onItemClickCallback.onItemClicked(listArt[holder.adapterPosition])
    }
  }

  interface OnItemClickCallback {
    fun onItemClicked(data: Art)
  }

  override fun getItemCount(): Int = listArt.size
}

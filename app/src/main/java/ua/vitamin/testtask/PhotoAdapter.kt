package ua.vitamin.testtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class PhotoAdapter(private val listPhoto: List<PhotoDTO>) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    override fun getItemCount() = listPhoto.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        Picasso.get().load(listPhoto[position].raw).into(holder.photo)
    }

    class PhotoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.item_photo_iv)
    }
}
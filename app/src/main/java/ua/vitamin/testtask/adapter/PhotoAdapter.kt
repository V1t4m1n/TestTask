package ua.vitamin.testtask.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.vitamin.testtask.R
import ua.vitamin.testtask.utils.dto.PhotoDTO

class PhotoAdapter(var listPhoto: List<PhotoDTO>?) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {

    override fun getItemCount(): Int = listPhoto?.size?:0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        Log.d("JSONHol", "${listPhoto?.size}")
        return PhotoHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        Picasso.get().load(listPhoto?.get(position)?.urls?.thumb).into(holder.photo)

        Log.d("JSONTH", "${listPhoto?.get(position)?.urls?.thumb}")
    }

    class PhotoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.item_photo_iv)
    }
}
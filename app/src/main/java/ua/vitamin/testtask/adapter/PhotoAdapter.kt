package ua.vitamin.testtask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import ua.vitamin.testtask.R
import ua.vitamin.testtask.screen.FullScreenImageDialog

class PhotoAdapter(var listPhoto: List<UnsplashPhoto>?, manager: FragmentManager) : RecyclerView.Adapter<PhotoAdapter.PhotoHolder>() {
    private var mManager: FragmentManager = manager
    override fun getItemCount(): Int = listPhoto?.size?:0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_photo,
            parent,
            false
        )
        return PhotoHolder(itemView)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        Picasso.get().load(listPhoto?.get(position)?.urls?.thumb).into(holder.photo)
    }

    inner class PhotoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.item_photo_iv)

        init {
            itemView.setOnClickListener {
                openImage(listPhoto?.get(adapterPosition)?.urls?.full)
            }
        }

        private fun openImage(imageURL: String?) {
            val dialog = FullScreenImageDialog(imageURL!!)
            dialog?.show(mManager, "FULL_SCREEN_DIALOG")
        }
    }
}
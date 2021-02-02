package ua.vitamin.testtask.screen

import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.squareup.picasso.Picasso
import ua.vitamin.testtask.R


class FullScreenImageDialog(val url: String) : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.image_dialog, container, false)

        val fullScreenImage: ImageView = root.findViewById(R.id.fullScreenImageView)

        Picasso.get().load(url).into(fullScreenImage)

        return root
    }
}
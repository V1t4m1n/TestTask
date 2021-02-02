package ua.vitamin.testtask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.unsplash.pickerandroid.photopicker.UnsplashPhotoPicker
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import com.unsplash.pickerandroid.photopicker.presentation.UnsplashPickerActivity
import ua.vitamin.testtask.adapter.PhotoAdapter
import ua.vitamin.testtask.utils.RequestManager
import ua.vitamin.testtask.utils.dto.PhotoDTO

class MainActivity : AppCompatActivity() {

    private lateinit var unsplashListImageRecyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: PhotoAdapter
    private var listPhoto: List<PhotoDTO>? = null
    private final val REQUEST_CODE = 201

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        UnsplashPhotoPicker.init(
            application,
            "ogn_r_XMWD2ph-AC7pnGPQEzPWKECEmotSsM93wYYKQ",
            "hjFltfdbPzm-sBaTwbSe6EFtkY-HQQPVfa-5tbgBeDk"
        )

        startActivityForResult(
            UnsplashPickerActivity.getStartingIntent(
                this, // context
                false
            ), REQUEST_CODE
        )
        initRecyclerView()

        swipeRefreshLayout.setOnRefreshListener {
            setListPhoto()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            val photos: List<UnsplashPhoto>? = data?.getParcelableArrayListExtra(UnsplashPickerActivity.EXTRA_PHOTOS)
            var list = RequestManager().onRequest()
            adapter = PhotoAdapter(photos, supportFragmentManager)
            unsplashListImageRecyclerView.adapter = adapter
        }
    }

    override fun onResume() {
        super.onResume()
        setListPhoto()
    }

    private fun setListPhoto() {
        var list = RequestManager().onRequest()
        adapter = PhotoAdapter(list, supportFragmentManager)
        unsplashListImageRecyclerView.adapter = adapter
    }

    private fun initRecyclerView() {
        unsplashListImageRecyclerView = findViewById(R.id.unsplashListImageRecyclerView)
        unsplashListImageRecyclerView.hasFixedSize()
        unsplashListImageRecyclerView.layoutManager =
            StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        unsplashListImageRecyclerView.adapter = PhotoAdapter(listOf(), supportFragmentManager)
    }
}
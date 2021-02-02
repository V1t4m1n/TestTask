package ua.vitamin.testtask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.vitamin.testtask.adapter.PhotoAdapter
import ua.vitamin.testtask.utils.api.UnsplashApi
import ua.vitamin.testtask.utils.dto.PhotoDTO

class MainActivity : AppCompatActivity() {

    private lateinit var unsplashListImageRecyclerView: RecyclerView
    private lateinit var adapter: PhotoAdapter
    private var listPhoto: List<PhotoDTO>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        var list = onRequest()
        Log.d("JSON_listPhoto", "${list?.get(0)?.urls?.thumb}")
        adapter = PhotoAdapter(list)
        unsplashListImageRecyclerView.adapter = adapter
    }

    private fun onRequest(): List<PhotoDTO>? {
        var list: List<PhotoDTO>? = null
        val retrofit = Retrofit.Builder().baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(UnsplashApi::class.java)
        val api: Call<List<PhotoDTO>> = service.getPhoto()

        api.enqueue(object : Callback<List<PhotoDTO>> {
            override fun onResponse(call: Call<List<PhotoDTO>>, response: Response<List<PhotoDTO>>) {
                list = response.body()
                Log.d("JSON_LIST", "${list?.get(0)?.urls?.thumb}")
            }

            override fun onFailure(call: Call<List<PhotoDTO>>, t: Throwable) {
                t.printStackTrace()
                Log.d("JSONT", t.localizedMessage.toString())
            }
        })
        return list
    }

    private fun initRecyclerView() {
        unsplashListImageRecyclerView = findViewById(R.id.unsplashListImageRecyclerView)
        unsplashListImageRecyclerView.hasFixedSize()
        unsplashListImageRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        unsplashListImageRecyclerView.adapter = PhotoAdapter(listOf())
    }
}
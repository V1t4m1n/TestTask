package ua.vitamin.testtask.utils

import android.util.Log
import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ua.vitamin.testtask.utils.api.UnsplashApi
import ua.vitamin.testtask.utils.dto.PhotoDTO

class RequestManager {

    public fun onRequest(): List<UnsplashPhoto>? {
        var list: List<UnsplashPhoto>? = null
        val retrofit = Retrofit.Builder().baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(UnsplashApi::class.java)
        val api: Call<List<UnsplashPhoto>> = service.getPhoto()

        api.enqueue(object : Callback<List<UnsplashPhoto>> {
            override fun onResponse(call: Call<List<UnsplashPhoto>>, response: Response<List<UnsplashPhoto>>) {
                list = response.body()
            }

            override fun onFailure(call: Call<List<UnsplashPhoto>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return list
    }
}
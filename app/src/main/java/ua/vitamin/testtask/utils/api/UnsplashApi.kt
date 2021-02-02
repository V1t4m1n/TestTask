package ua.vitamin.testtask.utils.api

import com.unsplash.pickerandroid.photopicker.data.UnsplashPhoto
import retrofit2.Call
import retrofit2.http.GET
import ua.vitamin.testtask.utils.dto.PhotoDTO

interface UnsplashApi {

    @GET("photos/?client_id=ogn_r_XMWD2ph-AC7pnGPQEzPWKECEmotSsM93wYYKQ")
    public fun getPhoto(): Call<List<UnsplashPhoto>>
}
package com.caucse.seoulproject.helper.`interface`

import com.google.gson.JsonArray
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageDownloadService {
    @GET fun download(@Url url : String) : Call<ResponseBody>
}
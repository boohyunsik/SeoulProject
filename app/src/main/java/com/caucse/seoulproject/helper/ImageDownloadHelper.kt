package com.caucse.seoulproject.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import com.caucse.seoulproject.R
import com.caucse.seoulproject.helper.`interface`.ImageDownloadService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Response.success
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayInputStream
import java.io.InputStream

open class ImageDownloadHelper {
    private val TAG = "ImageDownloadHelper"

    fun download(url : String) : Bitmap {
//        val retrofit : Retrofit = Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        val service : ImageDownloadService = retrofit.create(ImageDownloadService::class.java)
//        service.download(url).enqueue(object : Callback<ResponseBody> {
//            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
//                Log.d(TAG, "Successfully downloaded.")
//            }
//
//            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
//                Log.d(TAG, "Download failed.")
//            }
//        })

        Log.d(TAG, "download(), $url")
        val builder: StringBuilder = StringBuilder().append(url)
        val url = builder.toString()
        val response : String  = HttpHelper().execute(url).get()!!
        Log.d(TAG, response)

        val imageBytes = response.toByteArray()

        val bitmap : Bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return bitmap
    }
}

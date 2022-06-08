package com.example.orangetindertest.util

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiService {



    @GET("activity")
    fun getQuotes(): Call<JsonObject>


}

package com.example.SOOJOOB.retrofit

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface PloggingService {

    @Headers("X-Auth-Token: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJlbWFpbCI6ImhlbGxvQGdtYWlsLmNvbSIsImlhdCI6MTY2MDIxMzU1NywiZXhwIjoxNjYwMjI3OTU3fQ.K0lPuEA65rQG8Uj6ad_4uz8fFmDdvzx_5TfG06oAkWA",
    "Content-Type: application/json")
    @POST("plogging")
    fun addPloggingByEnqueue(@Body ploggingInfo: RequestBody): Call<PloggingResponseBody> // Call 은 흐름처리 기능을 제공해줌



    @Headers("X-Auth-Token: eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJoZWxsb0BnbWFpbC5jb20iLCJlbWFpbCI6ImhlbGxvQGdtYWlsLmNvbSIsImlhdCI6MTY2MDIxMzU1NywiZXhwIjoxNjYwMjI3OTU3fQ.K0lPuEA65rQG8Uj6ad_4uz8fFmDdvzx_5TfG06oAkWA",
        "Content-Type: application/json")
    @GET("plogging/all")
    fun getPlogging(): Call<PloggingGetResponseBody> // Call 은 흐름처리 기능을 제공해줌

}
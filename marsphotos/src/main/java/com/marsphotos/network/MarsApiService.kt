package com.marsphotos.network

import com.marsphotos.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
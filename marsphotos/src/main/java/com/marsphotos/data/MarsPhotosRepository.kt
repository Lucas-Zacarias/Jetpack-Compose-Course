package com.marsphotos.data

import com.marsphotos.model.MarsPhoto
import com.marsphotos.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }

}
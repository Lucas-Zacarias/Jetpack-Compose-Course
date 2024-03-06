package com.marsphotos.fake

import com.marsphotos.model.MarsPhoto
import com.marsphotos.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}
package com.marsphotos

import com.marsphotos.fake.FakeDataSource
import com.marsphotos.fake.FakeNetworkMarsPhotosRepository
import com.marsphotos.rules.TestDispatcherRule
import com.marsphotos.ui.MarsUiState
import com.marsphotos.ui.MarsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
        val marsViewModel = MarsViewModel(
            marsPhotosRepository = FakeNetworkMarsPhotosRepository()
        )
        assertEquals(
            MarsUiState.Success("Success: ${FakeDataSource.photosList.size} Mars photos retrieved"),
            marsViewModel.marsUiState
        )
    }

}
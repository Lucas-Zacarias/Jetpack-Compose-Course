package com.juicetraker.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.juicetraker.JuiceTrakerApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for ItemEntryViewModel
        initializer {
            EntryViewModel(juiceTrackerApplication().container.trackerRepository)
        }
        // Initializer for TrackerViewModel
        initializer {
            TrackerViewModel(juiceTrackerApplication().container.trackerRepository)
        }
    }
}
/**
 * Extension function to query for [Application] object and returns an instance of
 * [JuiceTrackerApplication].
 */
fun CreationExtras.juiceTrackerApplication(): JuiceTrakerApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as JuiceTrakerApplication)
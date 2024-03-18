package com.busschedule.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.busschedule.BusScheduleApplication
import com.busschedule.data.BusSchedule
import com.busschedule.data.BusScheduleRepository
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(
    private val repository: BusScheduleRepository
): ViewModel() {

    // Get example bus schedule
    fun getFullSchedule(): Flow<List<BusSchedule>> {
        return repository.getAllSchedules()
    }

    fun getScheduleFor(stopName: String): Flow<List<BusSchedule>> {
        return repository.getSchedule(stopName)
    }

    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BusScheduleApplication)
                val busScheduleRepository = application.container.busScheduleRepository
                BusScheduleViewModel(busScheduleRepository)
            }
        }
    }
}
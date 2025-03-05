package com.demo.demobaseandroid2.screens.gps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import android.location.Location
import com.demo.demobaseandroid2.domain.helper.LocationManager
import kotlinx.coroutines.launch

class LocationViewModel(private val locationManager: LocationManager) : ViewModel() {
    val location: StateFlow<Location?> = locationManager.location

    fun startTracking() {
        viewModelScope.launch {
            locationManager.startLocationUpdates()
        }
    }

    fun stopTracking() {
        locationManager.stopLocationUpdates()
    }

    override fun onCleared() {
        super.onCleared()
        stopTracking()
    }
}
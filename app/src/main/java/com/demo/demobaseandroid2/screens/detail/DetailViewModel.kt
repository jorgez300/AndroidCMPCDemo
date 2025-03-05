package com.demo.demobaseandroid2.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demobaseandroid2.domain.model.AuctionData
import com.demo.demobaseandroid2.domain.model.DetailState
import com.demo.demobaseandroid2.domain.service.DetailService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class DetailViewModel : ViewModel() {

    private val _service = DetailService()
    private val _uiState = MutableStateFlow<DetailState>(DetailState())
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val data = _service.fetchAuctionData()

            Log.d("DetailViewModel", data.toString())

            updateState(true, data)
        }
    }

    fun updateState(
        vip: Boolean = _uiState.value.vip,
        auctionData: List<AuctionData>? = _uiState.value.auctionData
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                vip = vip,
                auctionData = auctionData ?: emptyList()
            )
        }
    }
}

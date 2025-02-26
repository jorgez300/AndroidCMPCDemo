package com.demo.demobaseandroid2.screens.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.demobaseandroid2.domain.client.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuctionData(
    val dt: String,
    val winning_bid_max: Double,
    val winning_bid_min: Double,
    val winning_bid_mean: Double,
    val auction_trading_volume: Double,
    val auction_lots_count: Int,
    val all_auctions_lots_count: Int,
    val auction_name: String,
    val auction_slug: String
)


data class DetailState(
    val vip: Boolean = true,
    val auctionData: List<AuctionData> = emptyList()
)

class DetailViewModel : ViewModel() {


    private val _uiState = MutableStateFlow<DetailState>(DetailState())
    val uiState: StateFlow<DetailState> = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getData<List<AuctionData>>()
            if (response.isSuccessful) {
                Log.d("DetailViewModel", response.isSuccessful.toString())
                updateState(response.isSuccessful, response.body())
            } else {
                // Manejar error
            }
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

package com.demo.demobaseandroid2.domain.model

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
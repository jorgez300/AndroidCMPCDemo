package com.demo.demobaseandroid2.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun DetailScreen(navigateTo: (Any) -> Unit, viewModel: DetailViewModel) {

    Body(navigateTo, viewModel)

}

@Preview(
    showBackground = true,
)
@Composable
fun DetailScreenPreview() {

    Body({}, DetailViewModel())

}


@Composable
fun Body(navigateTo: (Any) -> Unit, viewModel: DetailViewModel) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    /*LazyColumn {
        items(uiState.auctionData.count()) { item ->
            Text(text = uiState.auctionData[item].auction_name)
        }
    }*/

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Detail Screen")
        Text(text = uiState.vip.toString())
    }
}

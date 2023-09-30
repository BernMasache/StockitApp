package com.example.stockitapp.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockitapp.model.CollectionModel

@Composable
fun CollectionsItem(collectionModel: CollectionModel) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(16.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
    Surface {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(collectionModel.collections.size) { item ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = collectionModel.collections[item].collection)
                    }
                    Divider()
                }
            }
    }
                }
}
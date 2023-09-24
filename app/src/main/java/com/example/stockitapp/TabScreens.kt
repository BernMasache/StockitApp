package com.example.stockitapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

enum class TabPage(val icon:ImageVector){
    Collections(Icons.Default.List),
    Registry(Icons.Default.Add)

}
@Composable
fun TabCollections(selectedTabIndex:Int, onSelectedTab:(TabPage)->Unit) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        TabPage.values().forEachIndexed { index, tabPage ->  Tab(
            selected = index==selectedTabIndex,
            onClick = {
                onSelectedTab(tabPage)
            },
            text = { Text(text = tabPage.name)},
            icon={ Icon(imageVector = tabPage.icon, contentDescription = null)}
        )}

    }
}
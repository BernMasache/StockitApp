package com.example.stockitapp

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.stockitapp.model.CollectionModel
import com.example.stockitapp.ui.theme.StockitAppTheme
import com.example.stockitapp.view.CollectionsItem
import com.example.stockitapp.viewModel.CollectionViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle

class MainActivity : ComponentActivity() {
    val collectionViewModel by viewModels<CollectionViewModel>()
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockitAppTheme {
                var tabPage by remember {
                    mutableStateOf(TabPage.Collections)
                }
                val pagerState = rememberPagerState()
                val scope = rememberCoroutineScope()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                TopAppBar(
                                    title = {
                                        Text("Stockit")
                                    }
                                )
                                TabCollections(
                                    selectedTabIndex = tabPage.ordinal
                                ) { it ->
                                    tabPage = it
                                }
                            }
                        }
                    ) {
                        Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
                            Spacer(modifier = Modifier.padding(72.dp))
                            collectionViewModel.getCollections()
                            when(tabPage.ordinal){
                                0-> CollectionsPage(name = tabPage.name, collectionsList = collectionViewModel.collectionsResponse, this@MainActivity)
                                1-> RegistryPage(this@MainActivity)

                            }


                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistryPage(context: Context) {
    var amount by remember {
        mutableStateOf("")
    }
    var numberOfDays by remember {
        mutableStateOf("")
    }
    var shopShare by remember {
        mutableStateOf("")
    }
    var otherExpenses by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var rentalFare by remember {
        mutableStateOf("")
    }

    var dateCollected by remember {
        mutableStateOf("")
    }

    var isExpanded by remember {
        mutableStateOf(false)
    }
    var rentalMonthlyRate by remember {
        mutableStateOf("")
    }
    val calendarState = rememberSheetState()
    CalendarDialog(state = calendarState,
        config = CalendarConfig(monthSelection = true,
            yearSelection = true,
            style = CalendarStyle.MONTH
        ),
        selection = CalendarSelection.Date{ date-> dateCollected=date.toString() })
    Column(
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())) {
        OutlinedTextField(value = amount.toString(), onValueChange = {
                it-> amount= it
        },label = { Text("Amount") } ,modifier = Modifier.fillMaxWidth(),keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
        OutlinedTextField(value = numberOfDays.toString(), onValueChange = {
                it->numberOfDays= it
        }, label = { Text("Number of days") },modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded=it}) {
            OutlinedTextField(
                value = rentalMonthlyRate,
                onValueChange ={},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                label = { Text("Monthly rental rate") },

                )
            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded=false }) {
                DropdownMenuItem(text = { Text(text = "800")}, onClick = {
                    rentalMonthlyRate="800"
                    isExpanded=false
                })
                DropdownMenuItem(text = { Text(text = "1000")}, onClick = {
                    rentalMonthlyRate="1000"
                    isExpanded=false
                })
                DropdownMenuItem(text = { Text(text = "0")}, onClick = {
                    rentalMonthlyRate="0"
                    isExpanded=false
                })
            }
        }
        OutlinedTextField(value = shopShare.toString(), onValueChange = {
                it->shopShare=it
        }, label = { Text("Shop share") },modifier = Modifier.fillMaxWidth(),keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        OutlinedTextField(value = otherExpenses.toString(), onValueChange = {
                it->otherExpenses=it
        }, label = { Text("Other expenses") },modifier = Modifier.fillMaxWidth(),keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        OutlinedTextField(value = description, onValueChange = {
                it->description=it
        }, label = { Text("Other expenses") },modifier = Modifier.fillMaxWidth())
        Row (
            modifier=Modifier.fillMaxWidth()){
            Button(onClick = { calendarState.show() },modifier=Modifier.weight(1f)) {
                Text(text = "Date Collected")
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(onClick = { /*TODO*/ },modifier=Modifier.weight(1f)) {
                Text(text = "Save")
            }
        }

    }
}

@Composable
fun CollectionsPage(name:String, collectionsList:List<CollectionModel>, context:Context) {
//    Log.d("Data",collectionsList.toString())
    Column (modifier= Modifier
        .fillMaxSize()
        .background(Color.White)){
            LazyColumn(modifier=Modifier.fillMaxSize()){
                item {  }
                itemsIndexed(items=collectionsList){
                        index, item ->  CollectionsItem(collectionModel = item)
                }
            }


    }

}
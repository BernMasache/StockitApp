package com.example.stockitapp.model

data class Model (
    val id:String,
    val collection:String,
    val dateCollected:String,
    val shopShare:String,
    val comment:String,
    val shareGryton:String,
    val numberOfDays:String,
    val rent:String,
    val expenses:String,
    val rentPerDay:String,
    val len : Array<Model>
)
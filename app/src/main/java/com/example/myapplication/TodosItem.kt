package com.example.myapplication

data class TodosItem(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface WebServise {

    @GET("/todos")
    suspend fun getTodos() : Todos
}
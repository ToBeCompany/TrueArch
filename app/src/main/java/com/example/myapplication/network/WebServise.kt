package com.example.myapplication.network

import com.example.myapplication.model.Todos
import retrofit2.Response
import retrofit2.http.GET

interface WebServise {

    @GET("/todos")
    suspend fun getTodos() : Response<Todos>
}
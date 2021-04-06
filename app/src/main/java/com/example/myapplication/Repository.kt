package com.example.myapplication


class Repository(private val reference: WebServise) {
    private var cache: Todos? = null

    suspend fun getTodos(): Todos? {
        if (cache == null) {
            cache = reference.getTodos()
        }
        return cache
    }
}
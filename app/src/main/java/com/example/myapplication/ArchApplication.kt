package com.example.myapplication

import android.app.Application

class ArchApplication : Application() {
        val repository: Repository by lazy { Repository(applicationContext) }
}
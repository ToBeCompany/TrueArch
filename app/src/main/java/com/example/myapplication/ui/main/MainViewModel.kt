package com.example.myapplication.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.ArchApplication
import com.example.myapplication.Repository
import com.example.myapplication.Resource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : Repository = (application as ArchApplication).repository

    val user : LiveData<Resource<String>> = repository.getUser()
}
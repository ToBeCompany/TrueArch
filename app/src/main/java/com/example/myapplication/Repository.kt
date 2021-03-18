package com.example.myapplication

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Repository(context: Context) {
    fun getUser() : LiveData<Resource<String>>{
        val ld = MutableLiveData<Resource<String>>(Resource.Loading())
        MainScope().launch {
            delay(7000)
                ld.postValue(Resource.Success<String>("EIFEGJQWEG"))
        }
        return ld
    }
}
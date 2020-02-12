package com.stupid.techie.m3biassignment.ui

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stupid.techie.m3biassignment.datalayer.FetchJokesIntentService
import com.stupid.techie.m3biassignment.model.Joke


class JokeViewModel(application: Application) :
    AndroidViewModel(application) {


    private val listOfJoke = ArrayList<Joke>()

    private val mData = MutableLiveData<List<Joke>>()

    fun getData(): LiveData<List<Joke>> {
        return mData
    }


    fun getJoke() {
        for (i in 0..9){
            val context = getApplication<Application>()
            val intent = Intent(context, FetchJokesIntentService::class.java)
            context.startService(intent)
        }

    }

    fun addToViewModelList(data: Joke) {
        listOfJoke.add(data)
        mData.value = listOfJoke
    }


}

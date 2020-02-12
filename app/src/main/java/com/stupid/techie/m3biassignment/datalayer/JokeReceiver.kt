package com.stupid.techie.m3biassignment.datalayer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stupid.techie.m3biassignment.model.Joke
import com.stupid.techie.m3biassignment.ui.JokeListFragment


class JokeReceiver : BroadcastReceiver(){

    private val mData = MutableLiveData<Joke>()

    fun getData(): LiveData<Joke> {
        return mData
    }

    override fun onReceive(context: Context, intent: Intent) {
        val joke = intent.getStringExtra(JokeListFragment.KEY_JOKE_DATA)
        Log.i("onRecieve ",joke)
        mData.postValue(Joke(joke))
    }

}
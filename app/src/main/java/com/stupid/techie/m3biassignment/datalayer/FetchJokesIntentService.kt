package com.stupid.techie.m3biassignment.datalayer

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.stupid.techie.m3biassignment.model.DataResponse
import com.stupid.techie.m3biassignment.network.FetchDataService
import com.stupid.techie.m3biassignment.network.RetrofitAPIClient
import com.stupid.techie.m3biassignment.ui.JokeListFragment
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchJokesIntentService : IntentService(FetchJokesIntentService::class.java.name) {

    private val TAG = FetchJokesIntentService::class.java.name
    lateinit var fetchDataService: FetchDataService


    companion object {
        var counter: Int = 0
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(
            "onCreate " + FetchJokesIntentService::class.java.name,
            "Called ${FetchJokesIntentService.counter++}  Times"
        )

        fetchDataService = RetrofitAPIClient.getApi()
    }

    override fun onHandleIntent(intent: Intent?) {

        Log.i("onHandleIntent ", "Called ${FetchJokesIntentService.counter++}  Times")
        callJokeService()
    }

    private fun callJokeService() {


        val call = fetchDataService.getJoke()

        call.enqueue(object : Callback<ResponseBody> {

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val gson = Gson()
                val response = gson.fromJson<DataResponse>(response.body()?.string(),DataResponse::class.java)

                Log.i(TAG," Joke $response.value.joke")
                val intent = Intent(JokeListFragment.RECEIVE_JOKE_ACTION)
                intent.putExtra(JokeListFragment.KEY_JOKE_DATA,response.value.joke)
                sendBroadcast(intent)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })


    }
}
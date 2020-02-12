package com.stupid.techie.m3biassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.stupid.techie.m3biassignment.ui.JokeListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, JokeListFragment.newInstance())
                .commitNow()
        }
    }
}

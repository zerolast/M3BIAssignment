package com.stupid.techie.m3biassignment.ui

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stupid.techie.m3biassignment.R
import com.stupid.techie.m3biassignment.adapter.JokeListAdapter
import com.stupid.techie.m3biassignment.datalayer.JokeReceiver
import com.stupid.techie.m3biassignment.model.Joke
import kotlinx.android.synthetic.main.joke_list_fragment.*


class JokeListFragment : Fragment() {

    private val TAG = JokeListFragment::class.java.simpleName

    companion object {
        fun newInstance() = JokeListFragment()
        const val RECEIVE_JOKE_ACTION = "JOKE_ACTION"
        const val KEY_JOKE_DATA = "JOKE_DATA"
    }

    private lateinit var viewAdapter: JokeListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var jokesList: ArrayList<Joke>
    private val mReceiver: JokeReceiver = JokeReceiver()
    private lateinit var viewModel: JokeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        jokesList = ArrayList()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "onCreateView")
        return inflater.inflate(
            R.layout.joke_list_fragment,
            container,
            false
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated")
        viewModel =
            ViewModelProviders.of(this).get(JokeViewModel::class.java)
        viewModel.getData().observe(this, Observer<List<Joke>> {
            jokesList.clear()
            jokesList.addAll(it)
            viewAdapter.notifyDataSetChanged()
        })

        mReceiver.getData().observe(this, Observer<Joke> {
            Log.i(JokeListFragment::class.java.name, "$it")
            viewModel.addToViewModelList(it)
        })


        viewAdapter = JokeListAdapter(jokesList)
        viewManager = LinearLayoutManager(this.requireContext())
        val mDividerItemDecoration = DividerItemDecoration(
            jokesListRecyclerView.context,
            (viewManager as LinearLayoutManager).orientation
        )
        jokesListRecyclerView.addItemDecoration(mDividerItemDecoration)
        jokesListRecyclerView.layoutManager = viewManager
        jokesListRecyclerView.adapter = viewAdapter


    }

    override fun onResume() {
        super.onResume()
        requireActivity().registerReceiver(mReceiver, IntentFilter(RECEIVE_JOKE_ACTION))
        viewModel.getJoke()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(mReceiver)
    }

}


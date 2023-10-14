package com.example.tmbdassesment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmbdassesment.adapter.MovieListAdapter
import com.example.tmbdassesment.databinding.ActivityMainBinding
import com.example.tmbdassesment.model.MovieListModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MovieViewModel
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MovieListAdapter()

        viewmodel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewmodel.fetchMovie(this)
        viewmodel.posts().observe(this, movieObserver)

        with(binding) {
            movieRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
            movieRecycler.adapter = adapter
        }

    }

    private val movieObserver = Observer<ArrayList<MovieListModel>> {
        if (it.isEmpty()) {
//            Log.d("msg",it.toString())
            Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
            return@Observer
        }

        adapter.clear()
        adapter.addItems(it)
    }
}
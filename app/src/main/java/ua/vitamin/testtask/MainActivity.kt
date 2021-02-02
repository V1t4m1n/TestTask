package ua.vitamin.testtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var unsplashListImageRecyclerView: RecyclerView
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        unsplashListImageRecyclerView.adapter = adapter

    }

    private fun initRecyclerView() {
        unsplashListImageRecyclerView = findViewById(R.id.unsplashListImageRecyclerView)
        unsplashListImageRecyclerView.hasFixedSize()
        unsplashListImageRecyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }



}
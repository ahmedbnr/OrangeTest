package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenprincipal4sim1.AppDataBase
import com.example.test.adapters.favoriteAdapter
import com.example.test.models.Quotes

lateinit var recylcerQuotesAdapter: favoriteAdapter
lateinit var quotesRecyclerView: RecyclerView
lateinit var dataBase: AppDataBase

class FavoritesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        var QTList : MutableList<Quotes> = ArrayList()
        dataBase = AppDataBase.getDatabase(applicationContext)

        quotesRecyclerView = findViewById(R.id.favRv)

        QTList = dataBase.quotesDao().getAllFavorites().distinctBy { it.activity } as MutableList<Quotes>



        recylcerQuotesAdapter = favoriteAdapter(QTList)
        quotesRecyclerView.adapter = recylcerQuotesAdapter

        quotesRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,
            false)

    }
}
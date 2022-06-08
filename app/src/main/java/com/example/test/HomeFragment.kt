package com.example.test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.daprlabs.cardstack.SwipeDeck
import com.daprlabs.cardstack.SwipeDeck.SwipeEventCallback
import com.example.examenprincipal4sim1.AppDataBase
import com.example.orangetindertest.util.ApiClient
import com.example.test.adapters.TinderAdapter
import com.example.test.models.Quotes
import com.example.test.models.Tinder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        setHasOptionsMenu(true);


        val courseModalArrayList = arrayListOf<Tinder>()
        val cardStack = root.findViewById(R.id.swipe_deck) as SwipeDeck
        val adapter = TinderAdapter(courseModalArrayList, root.context)


        ApiClient.apiService.getQuotes().enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                t.printStackTrace()

            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                if (response.isSuccessful && response.body() != null) {
                    val content = response.body()
                    if (response.code() == 200) {

                        val quotes = response.body()
                        courseModalArrayList.add(
                            Tinder(
                                quotes.get("activity").asString,
                            )
                        )


                    }


                }


                courseModalArrayList.shuffle();

                adapter.notifyDataSetChanged()

            }

        }
        )
        cardStack.setAdapter(adapter)

        val likeBtn = root.findViewById<Button>(R.id.like_btn)
        likeBtn.setOnClickListener {
            try {
                Log.e("TAG", "onCreateView: ${courseModalArrayList.first()}", )
                Log.e("TAG", "onCreateView: ${courseModalArrayList.last()}", )
                AppDataBase.getDatabase(requireContext().applicationContext).quotesDao().insert(
                    Quotes(
                        activity = courseModalArrayList.last().activity.toString(),

                    )
                )
            } catch (ex: Exception) {
                Toast.makeText(
                    context,
                    "Cannot add the Quote twice to Favorites !",
                    Toast.LENGTH_SHORT
                ).show()
            }
            cardStack.swipeTopCardRight(400)
        }
        val dislikeBtn = root.findViewById<Button>(R.id.dislike_btn)
        dislikeBtn.setOnClickListener {
            cardStack.swipeTopCardLeft(400)
        }
        val StarBtn = root.findViewById<Button>(R.id.fav_btn)

        StarBtn.setOnClickListener {

            val intent = Intent(this.context, FavoritesActivity::class.java)
            startActivity(intent)

        }



        cardStack.setEventCallback(object : SwipeEventCallback {
            override fun cardSwipedLeft(position: Int) {
                // on card swipped to right we are displaying a toast message.
                ApiClient.apiService.getQuotes().enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                        t.printStackTrace()

                    }

                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val content = response.body()
                            if (response.code() == 200) {

                                val quotes = response.body()
                                courseModalArrayList.add(
                                    Tinder(
                                        quotes.get("activity").asString,
                                    )
                                )


                            }


                        }


                        courseModalArrayList.shuffle();

                        adapter.notifyDataSetChanged()

                    }

                }
                )


            }

            override fun cardSwipedRight(position: Int) {
                // on card swipped to right we are displaying a toast message.
                ApiClient.apiService.getQuotes().enqueue(object : Callback<JsonObject> {
                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                        t.printStackTrace()

                    }

                    override fun onResponse(
                        call: Call<JsonObject>,
                        response: Response<JsonObject>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            val content = response.body()
                            if (response.code() == 200) {

                                val quotes = response.body()
                                courseModalArrayList.add(
                                    Tinder(
                                        quotes.get("activity").asString,
                                    )
                                )
                                try {
                                    Log.e("TAG", "onCreateView: ${courseModalArrayList.first()}", )
                                    Log.e("TAG", "onCreateView: ${courseModalArrayList.last()}", )
                                    AppDataBase.getDatabase(requireContext().applicationContext).quotesDao().insert(
                                        Quotes(
                                            activity = courseModalArrayList.last().activity.toString(),

                                            )
                                    )
                                } catch (ex: Exception) {
                                    Toast.makeText(
                                        context,
                                        "Cannot add the Quote twice to Favorites !",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                            }


                        }


                        courseModalArrayList.shuffle();

                        adapter.notifyDataSetChanged()

                    }

                }
                )


            }

            override fun cardsDepleted() {
                // this method is called when no card is present
                Toast.makeText(activity, "No more courses present", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun cardActionDown() {
                // this method is called when card is swipped down.
                Log.i("TAG", "CARDS MOVED DOWN")
            }

            override fun cardActionUp() {
                // this method is called when card is moved up.
                Log.i("TAG", "CARDS MOVED UP")
            }
        })


        return root
    }


}
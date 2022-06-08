package com.example.test.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenprincipal4sim1.AppDataBase
import com.example.test.R
import com.example.test.models.Quotes
import com.example.test.models.Tinder
import java.lang.Exception

class favoriteAdapter (var QTList: MutableList<Quotes>) : RecyclerView.Adapter<favoriteAdapter.favoriteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): favoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_quote, parent, false)
        return favoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: favoriteViewHolder, position: Int) {
        val FavQuote = QTList[position].activity
        holder.QT.text =FavQuote


       /* holder.btn_delete.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete history for this book? ")
                .setMessage(holder.itemView.context.getString(R.string.deleteMessageUniv, bookList[position].Nom))
                .setPositiveButton("Yes"){ dialogInterface, which ->
                    AppDataBase.getDatabase(holder.itemView.context).booksDao().delete(bookList[position])
                    bookList.removeAt(position)
                    notifyDataSetChanged()

                }.setNegativeButton("No"){dialogInterface, which ->
                    dialogInterface.dismiss()
                }.create().show()

        }*/
    }

    override fun getItemCount(): Int = QTList.size

    class favoriteViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val QT = itemView.findViewById<TextView>(R.id.item_QT_description)

    }
}
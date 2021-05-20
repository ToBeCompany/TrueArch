package com.example.myapplication.ui.main

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class NumberAdapter : ListAdapter<Int, NumberAdapter.NumberViewHolder>(
    NumberDiffUtils()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        return NumberViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.simple_list_item_1, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.text1)
        fun onBind(num: Int) {
            Log.d("ITEM","NEEEEW")
            textView.text = num.toString()
        }
    }

    class NumberDiffUtils : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Int, newItem: Int) = oldItem == newItem
    }
}
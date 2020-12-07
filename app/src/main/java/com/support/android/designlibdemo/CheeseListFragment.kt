/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.support.android.designlibdemo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.support.android.designlibdemo.Cheeses.randomCheeseDrawable
import java.util.ArrayList

class CheeseListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rv = inflater.inflate(
            R.layout.fragment_cheese_list,
            container,
            false
        ) as RecyclerView
        rv.layoutManager = LinearLayoutManager(rv.context)
        rv.adapter = SimpleStringRecyclerViewAdapter(Cheeses.STRINGS.randomSublist(30))

        return rv
    }

    class SimpleStringRecyclerViewAdapter(
        private val values: List<String>
    ) : RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>() {

        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            var boundString: String? = null
            val image: ImageView = view.findViewById(R.id.avatar)
            val text: TextView = view.findViewById(android.R.id.text1)

            override fun toString(): String {
                return super.toString() + " '" + text.text
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.list_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.boundString = values[position]
            holder.text.text = values[position]

            holder.view.setOnClickListener { v ->
                val context = v.context
                val intent = Intent(context, CheeseDetailActivity::class.java)
                intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.boundString)
                context.startActivity(intent)
            }

            Glide.with(holder.image.context)
                .load(randomCheeseDrawable)
                .apply(RequestOptions().fitCenter())
                .into(holder.image)
        }

        override fun getItemCount(): Int = values.size
    }
}

private fun <T> Array<T>.randomSublist(amount: Int): List<T> {
    val list = ArrayList<T>(amount)
    while (list.size < amount) {
        list.add(random())
    }
    return list
}
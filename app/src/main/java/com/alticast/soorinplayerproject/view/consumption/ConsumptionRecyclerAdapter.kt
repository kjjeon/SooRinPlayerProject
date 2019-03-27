package com.alticast.soorinplayerproject.view.consumption

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.alticast.soorinplayerproject.R
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

class ConsumptionRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemsClickedRelay = PublishRelay.create<String>()
    private val items = mutableListOf<String>()

    fun setItems(items: List<String>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun itemSelected(): Observable<String> = itemsClickedRelay

    fun getItemAt(position: Int): String {
        return items[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RootMenuHolder( LayoutInflater.from(parent.context).inflate(
            R.layout.menu_root_item,
            parent,
            false
        ))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = items[position]

        when (holder) {
            is RootMenuHolder ->
                holder.run {
                    menuText.text = items[position]
                    clicked()
                        .map { item }
                        .subscribe(itemsClickedRelay)
                }
        }
    }

    inner class RootMenuHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuText: TextView by lazy { itemView.findViewById<TextView>(R.id.menu_text) }
       private val menuItem: LinearLayout by lazy { itemView.findViewById<LinearLayout>(R.id.menu_item) }
        fun clicked() = menuItem.clicks()
    }
}
package com.example.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.TextView

class ProjectAdapter : BaseAdapter, ListAdapter{
    var list = ArrayList<String>()
    var context : Context

    constructor(list: ArrayList<String>, context: Context){
        this.list = list
        this.context = context
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?) : View? {
        var view = p1
        if(view == null){
            var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_element, null)
        }

        //Handle TextView and display string
        var listItemText = view?.findViewById<TextView>(R.id.list_item_string)
        listItemText?.text = list[p0]

        //Handle buttons
        TODO("Add button function")

        return view
    }

    override fun getItem(p0: Int): Any {
        return list[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return list.size
    }

}
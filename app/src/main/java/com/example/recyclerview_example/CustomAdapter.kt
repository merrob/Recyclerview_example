package com.example.tsumfilter

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_example.R
import java.io.InputStream

internal class CustomAdapter(private var itemsList: Array<String>,context: Context): RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){
    lateinit var inputStream: InputStream
    lateinit var resourceId: Drawable
    val assetManager: AssetManager = context.resources.assets
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        var itemTextView:TextView = view.findViewById(R.id.itemTextView)
        val tsumImage: ImageView = view.findViewById(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList.get(position)
        holder.itemTextView.text = item

        inputStream = assetManager.open("tsumImages/${item}")
        resourceId = Drawable.createFromStream(inputStream, null)!!
        holder.tsumImage.setImageDrawable(resourceId)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
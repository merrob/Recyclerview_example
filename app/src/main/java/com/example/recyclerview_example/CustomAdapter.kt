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

internal class CustomAdapter(
    private var itemsList: MutableList<String>,
    private var itemLimit: MutableList<String>,
    private var itemDescription: MutableList<String>,
    context: Context): RecyclerView.Adapter<CustomAdapter.MyViewHolder>(){
    lateinit var inputStream: InputStream
    lateinit var resourceId: Drawable
    val assetManager: AssetManager = context.resources.assets
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

        var tsumName:TextView = view.findViewById(R.id.tsumname)
        val tsumImage: ImageView = view.findViewById(R.id.itemImageView)
        var tsumLimit: TextView = view.findViewById(R.id.tsumlimit)
        val tsumDescript: TextView = view.findViewById(R.id.tsumdescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rowlayout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = itemsList[position]
        val itemLimit = itemLimit[position]
        val itemDescript = itemDescription[position]
        holder.tsumName.text = item
        holder.tsumLimit.text = itemLimit
        holder.tsumDescript.text = itemDescript
        inputStream = assetManager.open("tsumImages/${item}.jpg")
        resourceId = Drawable.createFromStream(inputStream, null)!!
        holder.tsumImage.setImageDrawable(resourceId)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}
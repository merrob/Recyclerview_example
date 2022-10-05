package com.example.recyclerview_example

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream

public class ImageAdapter(private var itemsList: Array<String>, context: Context):RecyclerView.Adapter<ImageAdapter.MyTsumViewHolder>() {
    lateinit var inputStream: InputStream
    lateinit var resourceId: Drawable
    val assetManager:AssetManager = context.resources.assets

    class MyTsumViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tsumImage:ImageView = view.findViewById(R.id.itemImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTsumViewHolder {
        val itemTsumView = LayoutInflater.from(parent.context).inflate(R.layout.rowlayoutimage,parent,false)
        return MyTsumViewHolder(itemTsumView)
    }

    override fun onBindViewHolder(holder: MyTsumViewHolder, position: Int) {
        val item = itemsList[position]
        inputStream = assetManager.open("tsumImages/${item}")
        resourceId = Drawable.createFromStream(inputStream, null)!!
        holder.tsumImage.setImageDrawable(resourceId)

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
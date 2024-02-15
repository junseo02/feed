package com.study.feed.layout

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity
import com.study.feed.R

class CommunityAdapter(val context: Context, private val communityList: ArrayList<CommunityVo>):RecyclerView.Adapter<CommunityAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommunityAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.community, parent, false)
    return ViewHolder(view)
  }

  inner class ViewHolder(view: View): RecyclerView.ViewHolder(view!!) {
    private var communityButton = view?.findViewById<AppCompatButton>(R.id.communityButton)

    fun bind(communityVo: CommunityVo, context: Context) {
      communityButton?.text = communityVo.title
    }
  }

  override fun onBindViewHolder(holder: CommunityAdapter.ViewHolder, position: Int) {
    val constraintLayout = holder.itemView.layoutParams
    constraintLayout.width = (MainActivity.widthRate * 130).toInt()
    constraintLayout.height = (MainActivity.widthRate * 138).toInt()

    holder.bind(communityList[position], context)
  }

  override fun getItemCount(): Int {
    return communityList.size
  }
}
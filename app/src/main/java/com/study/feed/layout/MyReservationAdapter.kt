package com.study.feed.layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity.Companion.responsiveHeight
import com.study.feed.MainActivity.Companion.responsiveWidth
import com.study.feed.R

class MyReservationAdapter(val context: Context, private var reservation: ArrayList<MyReservationVo>):RecyclerView.Adapter<MyReservationAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReservationAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.my_reservation_item, parent, false)
    return ViewHolder(view)
  }

  inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
    var reservationDate = view?.findViewById<TextView>(R.id.reservationDate)
    var reservationTitle = view?.findViewById<TextView>(R.id.reservationTitle)
    var reservationTime = view?.findViewById<TextView>(R.id.reservationTime)

    val reservationTitleLayoutParams = reservationTitle?.layoutParams as ConstraintLayout.LayoutParams
    val reservationTimeLayoutParams = reservationTime?.layoutParams as ConstraintLayout.LayoutParams

    fun bind(myReservationVo: MyReservationVo, context: Context) {
      reservationDate?.text = myReservationVo.reservationDate
      reservationTitle?.text = myReservationVo.reservationTitle
      reservationTime?.text = myReservationVo.reservationTime
    }

  }

  override fun onBindViewHolder(holder: MyReservationAdapter.ViewHolder, position: Int) {
    holder.reservationDate?.layoutParams?.width = 67.responsiveWidth()
    holder.reservationDate?.layoutParams?.height = 32.responsiveHeight()
    holder.reservationTitle?.layoutParams?.width = 185.responsiveWidth()
    holder.reservationTitle?.layoutParams?.height = 32.responsiveHeight()
    holder.reservationTitleLayoutParams.setMargins(9.responsiveWidth(), 0, 0, 0)

    holder.reservationTime?.layoutParams?.width = 59.responsiveWidth()
    holder.reservationTime?.layoutParams?.height = 32.responsiveHeight()
    holder.reservationTimeLayoutParams.setMargins(0, 0, 4.responsiveWidth(), 0)
    holder.bind(reservation[position], context)
  }

  override fun getItemCount(): Int {
    return reservation.size
  }

}
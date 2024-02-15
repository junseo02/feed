package com.study.feed.layout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.study.feed.MainActivity
import com.study.feed.R

class MyReservationAdapter(val context: Context, private var reservation: ArrayList<MyReservationVo>):RecyclerView.Adapter<MyReservationAdapter.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReservationAdapter.ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.my_reservation_item, parent, false)
    return ViewHolder(view)
  }

  inner class ViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
    private var reservationDate = view?.findViewById<TextView>(R.id.reservationDate)
    private var reservationTitle = view?.findViewById<TextView>(R.id.reservationTitle)
    private var reservationTime = view?.findViewById<TextView>(R.id.reservationTime)

    val reservationTitleLayoutParams = reservationTitle?.layoutParams as ConstraintLayout.LayoutParams
    val reservationTimeLayoutParams = reservationTime?.layoutParams as ConstraintLayout.LayoutParams


    fun bind(myReservationVo: MyReservationVo, context: Context) {
      reservationDate?.text = myReservationVo.reservationDate
      reservationTitle?.text = myReservationVo.reservationTitle
      reservationTime?.text = myReservationVo.reservationTime

      reservationDate?.width = (MainActivity.widthRate * 67).toInt()
      reservationDate?.height = (MainActivity.heightRate * 32).toInt()
      reservationTitle?.width = (MainActivity.widthRate * 185).toInt()
      reservationTitle?.height = (MainActivity.heightRate * 32).toInt()
      reservationTitleLayoutParams.setMargins((MainActivity.widthRate * 9).toInt(), 0, 0, 0)

      reservationTime?.width = (MainActivity.widthRate * 59).toInt()
      reservationTime?.height = (MainActivity.heightRate * 32).toInt()
      reservationTimeLayoutParams.setMargins(0, 0, (MainActivity.widthRate * 4).toInt(), 0)


    }

  }

  override fun onBindViewHolder(holder: MyReservationAdapter.ViewHolder, position: Int) {
    holder.bind(reservation[position], context)
  }

  override fun getItemCount(): Int {
    return reservation.size
  }

}
package com.kadance.taxi.common.view.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakewharton.rxbinding2.view.RxView
import com.kadance.taxi.R
import com.kadance.taxi.data.RPoint
import kotlinx.android.synthetic.main.item_point.view.*

/**
 * Created by Kenza on 12.04.2018.
 */
class RPointsAdapter( val delegate : RPointDelegate) : RecyclerView.Adapter<RPointsAdapter.RecyclerViewHolder>() {

    interface  RPointDelegate {
        fun onPointsRequested() : List<RPoint>
        fun onEditPoint( point :RPoint)
        fun onRemovePoint( point :RPoint)
    }



    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = delegate.onPointsRequested() [position]
        holder.bind(item)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemBinding = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_point, viewGroup, false)
        return RecyclerViewHolder(itemBinding, delegate)
    }

    override fun getItemCount(): Int {
        return  delegate.onPointsRequested().size
    }

    class RecyclerViewHolder( var view: View, val delegate: RPointDelegate) : RecyclerView.ViewHolder(view) {

        @SuppressLint("CheckResult")
        fun bind(point : RPoint) {
            val title =  "${point.title}"
            val latLng = "lat: ${point.lat}  lng: ${point.lng}"

            RxView.clicks(itemView.edit).subscribe({
                delegate.onEditPoint(point)
            })

            RxView.clicks(itemView.remove).subscribe({
                delegate.onRemovePoint(point)
            })

            itemView.titleTV.text = title

            if(title.isEmpty()){
                itemView.visibility = View.GONE
            }
            itemView.latLngTV.text = latLng
        }
    }
}
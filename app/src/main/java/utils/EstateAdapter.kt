package utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.r_android_template.R
import kotlinx.android.synthetic.main.estate_item.view.*
import models.EstateModel

class EstateAdapter(
    private val estates: MutableList<EstateModel>,
    private val listener: OnItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<EstateAdapter.EstateViewHolder>() {

    inner class EstateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val tvEstateNo: TextView = itemView.tvEstateNo
        val tvEstateArea: TextView = itemView.tvEstateArea
        val tvEstateLand: TextView = itemView.tvEstateLand
        val tvEstateDistrict: TextView = itemView.tvEstateDistrict

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstateViewHolder {
        return EstateViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.estate_item,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: EstateViewHolder, position: Int) {
        val curEstate = estates[position]
        holder.apply {
            tvEstateNo.text = context.getString(R.string.estate_number, curEstate.estateNo)
            tvEstateArea.text = context.getString(R.string.area, curEstate.area)
            tvEstateLand.text = context.getString(R.string.land, curEstate.land)
            tvEstateDistrict.text = context.getString(R.string.district, curEstate.district)
        }
    }

    override fun getItemCount(): Int {
        return estates.size
    }


}
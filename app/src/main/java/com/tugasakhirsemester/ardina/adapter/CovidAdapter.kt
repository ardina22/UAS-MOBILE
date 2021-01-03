package com.tugasakhirsemester.ardina.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tugasakhirsemester.ardina.R
import com.tugasakhirsemester.ardina.model.Covid
import com.tugasakhirsemester.ardina.model.CovidItem
import kotlinx.android.synthetic.main.cardlist_covid.view.*

class CovidAdapter(private  val context: Context) : RecyclerView.Adapter<CovidAdapter.ViewHolder>() {

    private var covidList: Covid = Covid()

    fun setCovidList(covidList: Covid) {
        this.covidList = covidList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(context).inflate(
        R.layout.cardlist_covid, parent, false))

    override fun getItemCount(): Int = covidList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(covidList[position])

    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(covidItem: CovidItem) {
            with(itemView) {
                tv_provinsi.text = covidItem.attributes.Provinsi
                tv_kasus_positif.text = covidItem.attributes.Kasus_Posi.toString()
                tv_kasus_meninggal.text = covidItem.attributes.Kasus_Meni.toString()
                tv_kasus_sembuh.text = covidItem.attributes.Kasus_Semb.toString()
            }
        }
    }
}
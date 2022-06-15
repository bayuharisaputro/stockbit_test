package com.example.stockbit_test.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.stockbit_test.R
import com.example.stockbit_test.model.Data
import kotlinx.android.synthetic.main.item_data.view.*


class DataListAdapter(var listData: ArrayList<Data?>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    companion object {
        const val TYPE_LOADING = 0
        const val TYPE_CONTENT = 1
    }

    override fun getItemCount(): Int = listData.size

    override fun getItemId(position: Int): Long {
        val data: Data = listData[position]?:return 0
        return data.CoinInfo?.Id?.toLong()?:0L
    }

    override fun getItemViewType(position: Int): Int = if(listData[position] == null) TYPE_LOADING else TYPE_CONTENT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_LOADING -> {
                HolderLoading(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false))
            }
            else -> {
                HolderContent(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false))
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindableHolder)?.bind(position)
    }

    inner class HolderContent(itemView: View) : RecyclerView.ViewHolder(itemView), BindableHolder {
        private val mName = itemView.txt_name
        private val mFullName = itemView.txt_name_full_name
        private val mLastPrice = itemView.txt_last_price
        private val mPriceChanges = itemView.txt_price_changes
        init {

        }

        override fun bind(pos: Int) {
            val data = listData[pos]?:return
            mName.text = data.CoinInfo?.Name?:""
            mFullName.text = data.CoinInfo?.FullName?:""
            mLastPrice.text = data.DISPLAY?.USD?.PRICE?:""
            if((data.DISPLAY?.USD?.CHANGEPCT24HOUR?.toDouble() ?: 0.0) < 0) {
                mPriceChanges.setTextColor(ContextCompat.getColor(itemView.context, R.color.red_price))
            }
             else {
                mPriceChanges.setTextColor(ContextCompat.getColor(itemView.context, R.color.green_bibit))
            }
            mPriceChanges.text = "${data.DISPLAY?.USD?.CHANGE24HOUR} (${data.DISPLAY?.USD?.CHANGEPCT24HOUR}%)"
        }
    }

    inner class HolderLoading(itemView: View) : RecyclerView.ViewHolder(itemView), BindableHolder {

        override fun bind(pos: Int) {
        }
    }
}
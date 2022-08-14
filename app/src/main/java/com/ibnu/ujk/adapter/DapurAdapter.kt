package com.ibnu.ujk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.ujk.databinding.ItemListDapurBinding
import com.ibnu.ujk.model.CheckoutModel
import kotlin.collections.ArrayList

class DapurAdapter(
    var list: ArrayList<CheckoutModel>,
) : RecyclerView.Adapter<DapurAdapter.ViewHolder>() {

    private var saatItemDiKlik: ((CheckoutModel) -> Unit)? = null

    class ViewHolder(val binding: ItemListDapurBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListDapurBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.apply {
            binding.apply {
                tvItemDpNoMeja.text = item.noMeja
                tvItemDpNama.text = item.nama
                tvIteDpJumlah.text = "x${item.jumlah}"
                tvItemDpTime.text = item.waktu
            }

            itemView.setOnClickListener { saatItemDiKlik?.invoke(item) }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun aturSaatItemDiKlik(panggiBalik: (CheckoutModel) -> Unit) {
        this.saatItemDiKlik = panggiBalik
    }

    @SuppressLint("NotifyDataSetChanged")
    fun tambahData(item: ArrayList<CheckoutModel>) {
        this.list = item
        notifyDataSetChanged()
    }

}
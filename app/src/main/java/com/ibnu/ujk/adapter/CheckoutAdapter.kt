package com.ibnu.ujk.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.ujk.databinding.ItemListCheckoutBinding
import com.ibnu.ujk.model.CheckoutModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutAdapter(
    var list: ArrayList<CheckoutModel>,
) : RecyclerView.Adapter<CheckoutAdapter.ViewHolder>() {

    private var saatItemDiKlik: ((CheckoutModel) -> Unit)? = null

    class ViewHolder(val binding: ItemListCheckoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListCheckoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.apply {
            binding.apply {
                tvItemCoNama.text = item.nama
                tvItemCoJumlah.text = "x${item.jumlah}"
                tvItemCoHarga.text = formatRupiah(item.harga!!.toDouble())

                itemView.setOnClickListener {
                    saatItemDiKlik?.invoke(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun formatRupiah(number: Double): String {
        val localID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        return formatRupiah.format(number)
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
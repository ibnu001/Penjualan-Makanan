package com.ibnu.ujk.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ibnu.ujk.MenuDetailActivity
import com.ibnu.ujk.R
import com.ibnu.ujk.databinding.ItemListMenuBinding
import com.ibnu.ujk.model.MenuModel

class MenuFragmentAdapter(
    var list: ArrayList<MenuModel>,
) : RecyclerView.Adapter<MenuFragmentAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListMenuBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListMenuBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.apply {
            binding.apply {
                Glide.with(root)
                    .load(item.gambar)
                    .placeholder(R.drawable.bg_corner)
                    .error(R.drawable.bg_corner)
                    .fitCenter()
                    .transform(RoundedCorners(20))
                    .into(ivItemMenuGambar)

                tvItemMenuNama.text = item.nama

                itemView.setOnClickListener {
                    val intentMenuDetail = Intent(itemView.context, MenuDetailActivity::class.java)

                    intentMenuDetail.apply {
                        putExtra(MenuDetailActivity.EXTRA_KATEGORI, item.kategori)
                        putExtra(MenuDetailActivity.EXTRA_NAMA, item.nama)
                        putExtra(MenuDetailActivity.EXTRA_HARGA, item.harga)
                        putExtra(MenuDetailActivity.EXTRA_IMG, item.gambar)
                        putExtra(MenuDetailActivity.EXTRA_DESKRIPSI, item.deskripsi)
                    }
                    itemView.context.startActivity(intentMenuDetail)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}
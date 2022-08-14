package com.ibnu.ujk

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.google.android.material.tabs.TabLayoutMediator
import com.ibnu.ujk.adapter.MenuFragmentAdapter
import com.ibnu.ujk.adapter.MenuViewPagerAdapter
import com.ibnu.ujk.databinding.ActivityCheckoutBinding
import com.ibnu.ujk.databinding.ActivityMenuBinding
import com.ibnu.ujk.model.MenuModel

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    private lateinit var list: ArrayList<MenuModel>
    private lateinit var adapter: MenuFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val abTitle = (Html.fromHtml("<font color=\"#E7E7F3\">" + "Menu" + "</font>"))
        supportActionBar!!.title = abTitle

        list = arrayListOf()
        adapter = MenuFragmentAdapter(list)

        binding.vpMenu.adapter = MenuViewPagerAdapter(this@MenuActivity)

        TabLayoutMediator(binding.tabLayout, binding.vpMenu) { tab, index ->
            tab.text = when (index) {
                0 -> "Makanan"
                1 -> "Minuman"
                2 -> "Dessert"

                else -> throw Resources.NotFoundException("Posisi Tidak Ditemukan")
            }
        }.attach()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onSupportNavigateUp()
    }
}
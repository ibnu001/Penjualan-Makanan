package com.ibnu.ujk.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ibnu.ujk.fragment.DessertMenuFragment
import com.ibnu.ujk.fragment.MakananMenuFragment
import com.ibnu.ujk.fragment.MinumanMenuFragment

class MenuViewPagerAdapter(
    fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MakananMenuFragment()
            1 -> MinumanMenuFragment()
            2 -> DessertMenuFragment()

            else -> throw Resources.NotFoundException("Posisi Tidak Ditemukan")
        }
    }
}
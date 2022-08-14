package com.ibnu.ujk.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.ujk.R
import com.ibnu.ujk.adapter.MenuFragmentAdapter
import com.ibnu.ujk.datamenu.DataMenu
import com.ibnu.ujk.model.MenuModel

class MinumanMenuFragment : Fragment() {

    private lateinit var rv_list: RecyclerView
    private lateinit var list: ArrayList<MenuModel>
    private lateinit var adapter: MenuFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_minuman_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list = arrayListOf()
        rv_list = view.findViewById(R.id.rv_menu_minuman)

        adapter = MenuFragmentAdapter(list)

        list.addAll(DataMenu.itemListMinuman)

        rv_list.layoutManager = GridLayoutManager(this.context, 2)
        rv_list.setHasFixedSize(true)
        rv_list.adapter = adapter
    }
}
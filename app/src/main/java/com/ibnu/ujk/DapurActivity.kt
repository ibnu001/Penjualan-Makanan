package com.ibnu.ujk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.ujk.adapter.DapurAdapter
import com.ibnu.ujk.database.SqliteHelper
import com.ibnu.ujk.databinding.ActivityCheckoutBinding
import com.ibnu.ujk.databinding.ActivityDapurBinding
import com.ibnu.ujk.model.CheckoutModel

class DapurActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDapurBinding

    private lateinit var sqlite: SqliteHelper
    private lateinit var list: ArrayList<CheckoutModel>
    private lateinit var adapter: DapurAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDapurBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val abTitle = (Html.fromHtml("<font color=\"#E7E7F3\">" + "Dapur" + "</font>"))
        supportActionBar!!.title = abTitle

        list = arrayListOf()
        adapter = DapurAdapter(list)
        sqlite = SqliteHelper(this)

        showData()

        binding.apply {
            rvDapur.adapter = adapter
            rvDapur.layoutManager = LinearLayoutManager(this@DapurActivity)
            rvDapur.setHasFixedSize(true)
        }

        adapter.aturSaatItemDiKlik { hapusDapur(it.id!!) }

    }

    private fun hapusDapur(id: Int) {
        val dialog = this.let { AlertDialog.Builder(it) }

        dialog.setMessage("Anda yakin ingin menghapus?")
        dialog.setCancelable(true)
        dialog.setPositiveButton("Ya") { log, _ ->
            sqlite.hapusPesan(id)
            showData()
            log.dismiss()
        }

        dialog.setNegativeButton("Tidak") { log, _ ->
            log.dismiss()
        }

        val alert = dialog.create()
        alert.show()
    }

    private fun showData() {
        val cm = sqlite.ambilDapur()
        adapter.tambahData(cm)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onSupportNavigateUp()
    }
}
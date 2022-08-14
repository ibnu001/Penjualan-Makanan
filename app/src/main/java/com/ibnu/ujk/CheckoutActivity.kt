package com.ibnu.ujk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.ujk.adapter.CheckoutAdapter
import com.ibnu.ujk.database.SqliteHelper
import com.ibnu.ujk.databinding.ActivityCheckoutBinding
import com.ibnu.ujk.model.CheckoutModel
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckoutBinding

    private lateinit var sqlite: SqliteHelper
    private lateinit var list: ArrayList<CheckoutModel>
    private lateinit var adapter: CheckoutAdapter

    private lateinit var noMeja: String

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = arrayListOf()
        adapter = CheckoutAdapter(list)
        sqlite = SqliteHelper(this)

        val prefs = getSharedPreferences("no_meja_pref", Context.MODE_PRIVATE)

        val abTitle = (Html.fromHtml("<font color=\"#E7E7F3\">" + "Checkout" + "</font>"))
        supportActionBar!!.title = abTitle

        noMeja = prefs.getString("no_meja_pref", "").toString()

        binding.apply {
            tvCoNoMeja.text = "Nomor Meja : $noMeja"

            showData()

            rvCheckout.adapter = adapter
            rvCheckout.layoutManager = LinearLayoutManager(this@CheckoutActivity)
            rvCheckout.setHasFixedSize(true)

            adapter.aturSaatItemDiKlik { hapusCheckout(it.id!!) }

            btCoTambah.setOnClickListener {
                startActivity(Intent(this@CheckoutActivity, MenuActivity::class.java))
            }

            btCoKirim.setOnClickListener {
                startActivity(Intent(this@CheckoutActivity, DapurActivity::class.java))
            }
        }
    }

    private fun hapusCheckout(id: Int) {
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

    @SuppressLint("SetTextI18n")
    private fun showData() {
        val cm = sqlite.ambilCheckout(noMeja)
        adapter.tambahData(cm)

        var total = 0
        for (item in adapter.list) {
            total += item.harga!!
        }

        binding.tvCoGrandTotal.text = "Grand Total : ${formatRupiah(total.toDouble())}"
    }

    private fun formatRupiah(number: Double): String {
        val localID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localID)
        return formatRupiah.format(number)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onSupportNavigateUp()
    }
}
package com.ibnu.ujk

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.ibnu.ujk.databinding.ActivityCheckoutBinding
import com.ibnu.ujk.databinding.ActivityMejaPesananBinding

class MejaPesananActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMejaPesananBinding

    private lateinit var noMeja: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMejaPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("no_meja_pref", Context.MODE_PRIVATE)

        val abTitle = (Html.fromHtml("<font color=\"#E7E7F3\">" + "Meja Pesanan" + "</font>"))
        supportActionBar!!.title = abTitle

        binding.apply {
            btPesananLanjut.setOnClickListener {
                noMeja = etPesananNoMeja.text.toString()
                val editor = prefs.edit()

                if (noMeja.isNotEmpty()) {
                    editor.putString("no_meja_pref", noMeja)
                    editor.apply()

                    startActivity(Intent(this@MejaPesananActivity, CheckoutActivity::class.java))
                } else {
                    Toast.makeText(
                        this@MejaPesananActivity, "Isi nomor meja dahulu", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onSupportNavigateUp()
    }
}
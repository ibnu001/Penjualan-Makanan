package com.ibnu.ujk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.ibnu.ujk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val abTitle = (Html.fromHtml("<font color=\"#E7E7F3\">" + "Cafe" + "</font>"))
        supportActionBar!!.title = abTitle

        binding.apply {
            btMenu.setOnClickListener {
                startActivity(Intent(this@MainActivity, MenuActivity::class.java))
            }

            btPesanan.setOnClickListener {
                startActivity(Intent(this@MainActivity, MejaPesananActivity::class.java))
            }

            btDapur.setOnClickListener {
                startActivity(Intent(this@MainActivity, DapurActivity::class.java))
            }
        }
    }
}
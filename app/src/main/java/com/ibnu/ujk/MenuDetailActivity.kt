package com.ibnu.ujk

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.ibnu.ujk.database.SqliteHelper
import com.ibnu.ujk.databinding.ActivityCheckoutBinding
import com.ibnu.ujk.databinding.ActivityMenuDetailBinding
import com.ibnu.ujk.model.CheckoutModel
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MenuDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuDetailBinding

    private lateinit var sqlite: SqliteHelper
    private lateinit var noMeja: String

    var jumlah: Int = 0

    companion object {
        const val EXTRA_KATEGORI = "kategori"
        const val EXTRA_NAMA = "nama"
        const val EXTRA_HARGA = "harga"
        const val EXTRA_IMG = "gambar"
        const val EXTRA_DESKRIPSI = "deskripsi"
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kategori = intent.getStringExtra(EXTRA_KATEGORI)
        val nama = intent.getStringExtra(EXTRA_NAMA)
        val harga = intent.getIntExtra(EXTRA_HARGA, 0)
        val gambar = intent.getIntExtra(EXTRA_IMG, 0)
        val deskripsi = intent.getStringExtra(EXTRA_DESKRIPSI)

        // buat title dan warna dari title tersebut
        val abTitle = (Html.fromHtml("<font color=\"#E7E7F3\">" + "$kategori" + "</font>"))
        supportActionBar!!.title = abTitle

        // menginisialisasi variable sqlite dengan database class SqliteHelper
        sqlite = SqliteHelper(this)

        // menyimpan nomor meja pada shared preferences
        val prefs = getSharedPreferences("no_meja_pref", Context.MODE_PRIVATE)
        noMeja = prefs.getString("no_meja_pref", "").toString()

        binding.apply {
            tvMenuDetailKategori.text = kategori
            tvMenuDetailNama.text = nama
            tvMenuDetailHarga.text = formatRupiah(harga.toDouble())
            tvMenuDetailDeskripsi.text = deskripsi

            Glide.with(this@MenuDetailActivity)
                .load(gambar)
                .fitCenter()
                .transform(RoundedCorners(20))
                .into(ivMenuDetailGambar)

            jumlah = tvMenuDetailJumlah.text.toString().toInt()

            // melakukan pertambahan jumlah produk
            btMenuDetailTambah.setOnClickListener {
                jumlah++
                tvMenuDetailJumlah.text = jumlah.toString()
                tvMenuDetailTotalHarga.text = "Total ${formatRupiah(jumlah * harga.toDouble())}"
            }

            // melakukan pengurangan jumlah produk
            btMenuDetailKurang.setOnClickListener {
                if (jumlah > 0) {
                    jumlah--
                    tvMenuDetailJumlah.text = jumlah.toString()
                    tvMenuDetailTotalHarga.text = "Total ${formatRupiah(jumlah * harga.toDouble())}"
                }
            }

            btMenuDetailPesan.setOnClickListener {
                if (jumlah > 0) {
                    val calendar = Calendar.getInstance()
                    val format = SimpleDateFormat("EEEE, d-MMMM-yyyy, HH:mm:ss")
                    val waktu = format.format(calendar.time)

                    if (noMeja.isNullOrEmpty()) {
                        val dialog = AlertDialog.Builder(this@MenuDetailActivity)

                        dialog.setMessage("Silakan masukkan nomor meja terlebih dahulu!")
                        dialog.setCancelable(true)
                        dialog.setPositiveButton("Ya") { log, _ ->
                            startActivity(Intent(this@MenuDetailActivity,
                                MejaPesananActivity::class.java))
                            log.dismiss()
                        }

                        dialog.setNegativeButton("Tidak") { log, _ ->
                            log.dismiss()
                        }

                        val warn = dialog.create()
                        warn.show()


                    } else {
                        val cm = CheckoutModel(
                            nama = nama,
                            harga = harga * jumlah,
                            jumlah = jumlah.toString(),
                            noMeja = noMeja,
                            waktu = waktu
                        )

                        val status = sqlite.tambahPesan(cm)
                        if (status > -1) {
                            startActivity(Intent(this@MenuDetailActivity,
                                CheckoutActivity::class.java))
                            Toast.makeText(this@MenuDetailActivity,
                                "Pesanan Ditambah",
                                Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this@MenuDetailActivity,
                                "Pesanan Gagal Ditambah",
                                Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                } else {
                    Toast.makeText(this@MenuDetailActivity, "Minimal 1 pesanan", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun formatRupiah(number: Double): String? {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(number)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onSupportNavigateUp()
    }
}
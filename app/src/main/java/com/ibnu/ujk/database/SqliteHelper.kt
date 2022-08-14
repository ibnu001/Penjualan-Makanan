package com.ibnu.ujk.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ibnu.ujk.model.CheckoutModel

class SqliteHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAMA, null, DATABASE_VERSI) {

    companion object {
        private const val DATABASE_NAMA = "ujk_cafe"
        private const val DATABASE_VERSI = 1

        private const val TABLE_PESANAN = "pesanan"
        private const val KOLOM_ID = "id"
        private const val KOLOM_NAMA = "nama"
        private const val KOLOM_HARGA = "harga"
        private const val KOLOM_JUMLAH = "jumlah"
        private const val KOLOM_NOMOR_MEJA = "nomor_meja"
        private const val KOLOM_WAKTU = "waktu"
    }

    @SuppressLint("SQLiteString")
    override fun onCreate(db: SQLiteDatabase?) {
        val tabelPesan = ("CREATE TABLE $TABLE_PESANAN (" +
                "$KOLOM_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$KOLOM_NAMA TEXT, " +
                "$KOLOM_HARGA INTEGER, " +
                "$KOLOM_JUMLAH STRING, " +
                "$KOLOM_NOMOR_MEJA STRING, " +
                "$KOLOM_WAKTU TEXT " +
                ")")
        db?.execSQL(tabelPesan)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_PESANAN")
    }

    fun tambahPesan(co: CheckoutModel): Long {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(KOLOM_ID, co.id)
        cv.put(KOLOM_NAMA, co.nama)
        cv.put(KOLOM_HARGA, co.harga)
        cv.put(KOLOM_JUMLAH, co.jumlah)
        cv.put(KOLOM_NOMOR_MEJA, co.noMeja)
        cv.put(KOLOM_WAKTU, co.waktu)

        val success = db.insert(TABLE_PESANAN, null, cv)
        db.close()

        return success
    }

    fun hapusPesan(no_index: Int): Int {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(KOLOM_ID, no_index)

        val success = db.delete(TABLE_PESANAN, "id=$no_index", null)
        db.close()

        return success
    }

    @SuppressLint("Recycle", "Range")
    fun ambilCheckout(noMeja: String): ArrayList<CheckoutModel> {
        val dataCheckout: ArrayList<CheckoutModel> = ArrayList()
        val queryTampil = "SELECT * FROM $TABLE_PESANAN WHERE $KOLOM_NOMOR_MEJA = $noMeja"
        val db = this.readableDatabase
        val kursor: Cursor

        try {
            kursor = db.rawQuery(queryTampil, null)
        } catch (kesalahan: Exception) {
            kesalahan.printStackTrace()
            db.execSQL(queryTampil)

            return ArrayList()
        }

        var id: Int?
        var nama: String?
        var harga: Int?
        var jumlah: String?
        var nomorMeja: String?
        var waktu: String?

        if (kursor.moveToFirst()) {
            do {

                id = kursor.getInt(kursor.getColumnIndex("id"))
                nama = kursor.getString(kursor.getColumnIndex("nama"))
                harga = kursor.getInt(kursor.getColumnIndex("harga"))
                jumlah = kursor.getString(kursor.getColumnIndex("jumlah"))
                nomorMeja = kursor.getString(kursor.getColumnIndex("nomor_meja"))
                waktu = kursor.getString(kursor.getColumnIndex("waktu"))

                val cm = CheckoutModel(id, nama, harga, jumlah, nomorMeja, waktu)
                dataCheckout.add(cm)

            } while (kursor.moveToNext())
        }
        return dataCheckout
    }

    @SuppressLint("Recycle", "Range")
    fun ambilDapur(): ArrayList<CheckoutModel> {
        val dataCheckout: ArrayList<CheckoutModel> = ArrayList()
        val queryTampil = "SELECT * FROM $TABLE_PESANAN"
        val db = this.readableDatabase
        val kursor: Cursor

        try {
            kursor = db.rawQuery(queryTampil, null)
        } catch (kesalahan: Exception) {
            kesalahan.printStackTrace()
            db.execSQL(queryTampil)

            return ArrayList()
        }

        var id: Int?
        var nama: String?
        var harga: Int?
        var jumlah: String?
        var nomorMeja: String?
        var waktu: String?

        if (kursor.moveToFirst()) {
            do {

                id = kursor.getInt(kursor.getColumnIndex("id"))
                nama = kursor.getString(kursor.getColumnIndex("nama"))
                harga = kursor.getInt(kursor.getColumnIndex("harga"))
                jumlah = kursor.getString(kursor.getColumnIndex("jumlah"))
                nomorMeja = kursor.getString(kursor.getColumnIndex("nomor_meja"))
                waktu = kursor.getString(kursor.getColumnIndex("waktu"))

                val cm = CheckoutModel(id, nama, harga, jumlah, nomorMeja, waktu)
                dataCheckout.add(cm)

            } while (kursor.moveToNext())
        }
        return dataCheckout
    }
}
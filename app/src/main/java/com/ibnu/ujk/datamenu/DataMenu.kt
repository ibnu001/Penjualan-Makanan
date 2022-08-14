package com.ibnu.ujk.datamenu

import com.ibnu.ujk.R
import com.ibnu.ujk.model.MenuModel

object DataMenu {

    /** Kategori **/
    private val menuKategori = arrayOf(
        "Makanan", "Minuman", "Dessert"
    )

    /** Makanan **/
    private val menuMakananNama = arrayOf(
        "Sup Buntut", "Gado-Gado", "Mie Ayam", "Sate Ayam", "Rawon"
    )

    private val menuMakananHarga = arrayOf(
        30_000, 20_000, 12_000, 15_000, 35_000
    )

    // gambar Makanan
    val supBuntut = R.drawable.sup_buntut
    val gadoGado = R.drawable.gado_gado
    val mieAyam = R.drawable.mie_ayam
    val sateAyam = R.drawable.sate_ayam
    val rawon = R.drawable.rawon

    private val menuMakananGambar = arrayOf(
        supBuntut, gadoGado, mieAyam, sateAyam, rawon
    )

    // deskripsi makanan
    var desSupBuntut = "Sop buntut adalah salah satu masakan populer dalam masakan Indonesia. Terbuat dari potongan ekor sapi yang dibumbui kemudian dibakar atau digoreng dan dimasukkan ke dalam kuah kaldu sapi yang agak bening bersama irisan kentang, wortel, tomat, daun bawang, seledri dan taburan bawang goreng."
    var desGadoGado = "Gado-gado adalah makanan khas Jakarta berisi sayur-sayuran yang direbus, irisan telur dan tahu, serta ditaburi bawang goreng dan kerupuk. Sayur-sayuran ditambahkan dengan bumbu kacang atau saus dari kacang tanah yang telah dihaluskan yang kemudian diaduk merata."
    var desMieAyam = "Mie ayam adalah hidangan khas Indonesia yang terbuat dari mi gandum kuning yang dibumbui dengan daging ayam yang biasanya dipotong dadu."
    var desSateAyam = "Pada umumnya sate ayam dimasak dengan cara dipanggang dengan menggunakan arang dan disajikan dengan pilihan bumbu kacang atau bumbu kecap."
    var desRawon = "Rawon adalah masakan Indonesia berupa sup daging berkuah hitam dengan campuran bumbu khas yang menggunakan kluwek."

    private val menuMakananDeskripsi = arrayOf(
        desSupBuntut, desGadoGado, desMieAyam, desSateAyam, desRawon
    )

    /** Minuman **/
    private val menuMinumanNama = arrayOf(
        "Es Teh", "Es Jeruk", "Jus Alpukat", "Jus Mangga", "Es Dawet"
    )

    private val menuMinumanHarga = arrayOf(
        5_000, 6_000, 10_000, 10_000, 12_000
    )

    // gambar Minuman
    val esTeh = R.drawable.es_teh
    val esJeruk = R.drawable.es_jeruk
    val jusAlpukat = R.drawable.jus_alpukat
    val jusmangga = R.drawable.jus_mangga
    val esDawet = R.drawable.es_dawet

    private val menuMinumanGambar = arrayOf(
        esTeh, esJeruk, jusAlpukat, jusmangga, esDawet
    )

    // deskripsi Minuman
    var desEsTeh = "Es teh adalah minuman yang sering diminum saat siang hari karena suhu udara yang panas."
    var desEsJeruk = "Biasanya minuman ini mengandung sedikit atau sari buah jeruk."
    var desJusAlpukat = "Biasanya minuman ini mengandung sedikit atau sari buah alpukat."
    var desJusMangga = "Biasanya minuman ini mengandung sedikit atau sari buah mangga."
    var desEsDawet = "Es Dawet adalah penganan yang dibuat dari tepung beras dan sebagainya yang dibentuk dengan penyaring, kemudian dicampur dengan air gula dan santan."

    private val menuMinumanDeskripsi = arrayOf(
        desEsTeh, desEsJeruk, desJusAlpukat, desJusMangga, desEsDawet
    )

    /** Dessert **/
    private val menuDessertNama = arrayOf(
        "Kue Putu", "Onde-Onde"
    )

    private val menuDessertHarga = arrayOf(
        6_000, 8_000
    )

    // gambar Dessert
    val kuePutu = R.drawable.kue_putu
    val onde = R.drawable.onde_onde

    private val menuDessertGambar = arrayOf(
        kuePutu, onde
    )

    // deskripsi Dessert
    var desKuePutu = "Kue putu adalah jenis kudapan tradisional Indonesia berupa kue dengan isian gula jawa, dibalut dengan parutan kelapa, dan tepung beras butiran kasar."
    var desOnde = "Onde-onde terbuat dari tepung terigu ataupun tepung ketan yang digoreng atau direbus dan permukaannya ditaburi/dibalur dengan biji wijen."


    private val menuDessertDeskripsi = arrayOf(
        desKuePutu, desOnde
    )

    /** Memasukan value data ke dalam data Menu Model **/

    /** Makanan **/
    val itemListMakanan: ArrayList<MenuModel>
        get() {
            val makanan = arrayListOf<MenuModel>()
            for (position in menuMakananNama.indices) {
                val makan = MenuModel()
                makan.kategori = menuKategori[0]
                makan.nama = menuMakananNama[position]
                makan.harga = menuMakananHarga[position]
                makan.gambar = menuMakananGambar[position]
                makan.deskripsi = menuMakananDeskripsi[position]
                makanan.add(makan)
            }
            return makanan
        }

    /** Minuman **/
    val itemListMinuman: ArrayList<MenuModel>
        get() {
            val minuman = arrayListOf<MenuModel>()
            for (position in menuMinumanNama.indices) {
                val minum = MenuModel()
                minum.kategori = menuKategori[1]
                minum.nama = menuMinumanNama[position]
                minum.harga = menuMinumanHarga[position]
                minum.gambar = menuMinumanGambar[position]
                minum.deskripsi = menuMinumanDeskripsi[position]
                minuman.add(minum)
            }
            return minuman
        }

    /** Dessert **/
    val itemListDessert: ArrayList<MenuModel>
        get() {
            val dessert = arrayListOf<MenuModel>()
            for (position in menuDessertNama.indices) {
                val dess = MenuModel()
                dess.kategori = menuKategori[2]
                dess.nama = menuDessertNama[position]
                dess.harga = menuDessertHarga[position]
                dess.gambar = menuDessertGambar[position]
                dess.deskripsi = menuDessertDeskripsi[position]
                dessert.add(dess)
            }
            return dessert
        }
}
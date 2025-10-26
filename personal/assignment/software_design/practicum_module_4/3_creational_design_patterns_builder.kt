package com.example.library.model

import com.example.library.LibraryManager

/**
 * Kelas Buku dengan pola Builder untuk membangun objek secara fleksibel.
 */
class Buku private constructor(
    val judul: String,
    val penulis: String,
    val tahunTerbit: Int?,
    val status: String
) {

    override fun toString(): String {
        return "Buku(judul='$judul', penulis='$penulis', tahunTerbit=$tahunTerbit, status='$status')"
    }

    /**
     * Builder untuk membangun objek Buku langkah demi langkah.
     */
    class Builder {
        private var judul: String = ""
        private var penulis: String = ""
        private var tahunTerbit: Int? = null
        private var status: String = "tersedia"

        fun judul(judul: String) = apply { this.judul = judul }
        fun penulis(penulis: String) = apply { this.penulis = penulis }
        fun tahunTerbit(tahunTerbit: Int) = apply { this.tahunTerbit = tahunTerbit }
        fun status(status: String) = apply { this.status = status }

        fun build(): Buku {
            require(judul.isNotBlank()) { "Judul buku tidak boleh kosong." }
            require(penulis.isNotBlank()) { "Penulis buku tidak boleh kosong." }
            return Buku(judul, penulis, tahunTerbit, status)
        }
    }
}

fun main() {
    val naruto = Buku.Builder()
        .judul("Naruto")
        .penulis("Masashi Kishimoto")
        .tahunTerbit(1999)
        .build()

    val chainsawMan = Buku.Builder()
        .judul("Chainsaw Man")
        .penulis("Tatsuki Fujimoto")
        .tahunTerbit(2018)
        .build()

    LibraryManager.addBook(naruto)
    LibraryManager.addBook(chainsawMan)

    LibraryManager.listAllBooks()
}


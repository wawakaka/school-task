package com.example.library.service

import com.example.library.model.Buku

/**
 * Interface untuk pustaka BookLibrary.
 * Menyediakan operasi dasar terhadap koleksi buku.
 */
interface BookLibrary {

    /**
     * Menambahkan buku baru ke dalam perpustakaan.
     */
    fun addBook(book: Buku)

    /**
     * Menghapus buku berdasarkan judul.
     * @return true jika berhasil dihapus, false jika tidak ditemukan.
     */
    fun removeBook(title: String): Boolean

    /**
     * Mencari buku berdasarkan judul.
     * @return Buku jika ditemukan, null jika tidak ada.
     */
    fun findBookByTitle(title: String): Buku?

    /**
     * Menampilkan semua buku yang ada.
     */
    fun getAllBooks(): List<Buku>
}

/**
 * Implementasi in-memory dari BookLibrary.
 * Dapat digunakan secara mandiri di berbagai aplikasi.
 */
class BookLibraryImpl : BookLibrary {

    private val daftarBuku = mutableListOf<Buku>()

    override fun addBook(book: Buku) {
        daftarBuku.add(book)
    }

    override fun removeBook(title: String): Boolean {
        val target = daftarBuku.find { it.judul.equals(title, ignoreCase = true) }
        return if (target != null) {
            daftarBuku.remove(target)
            true
        } else {
            false
        }
    }

    override fun findBookByTitle(title: String): Buku? {
        return daftarBuku.find { it.judul.equals(title, ignoreCase = true) }
    }

    override fun getAllBooks(): List<Buku> {
        return daftarBuku.toList() // return copy agar tidak bisa diubah dari luar
    }
}

fun main() {
    val library = BookLibraryImpl()

    library.addBook(Buku("Naruto", "Masashi Kishimoto"))
    library.addBook(Buku("Chainsaw Man", "Tatsuki Fujimoto"))

    println(library.findBookByTitle("Naruto"))
    println(library.getAllBooks())

    library.removeBook("Naruto")
    println(library.getAllBooks())
}

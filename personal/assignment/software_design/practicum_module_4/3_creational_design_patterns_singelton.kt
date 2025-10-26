package com.example.library

import com.example.library.model.Buku
import com.example.library.service.BookLibrary
import com.example.library.service.BookLibraryImpl

/**
 * Kelas LibraryManager bertanggung jawab mengelola operasi perpustakaan.
 * Menggunakan pola Singleton agar hanya ada satu instance selama runtime.
 */
object LibraryManager {

    // Menggunakan implementasi BookLibrary sebagai backend
    private val bookLibrary: BookLibrary = BookLibraryImpl()

    /**
     * Menambahkan buku baru ke sistem perpustakaan.
     */
    fun addBook(book: Buku) {
        bookLibrary.addBook(book)
        println("✅ Buku '${book.judul}' berhasil ditambahkan.")
    }

    /**
     * Menghapus buku berdasarkan judul.
     */
    fun removeBook(title: String) {
        val success = bookLibrary.removeBook(title)
        if (success) {
            println("🗑️ Buku '$title' berhasil dihapus.")
        } else {
            println("⚠️ Buku '$title' tidak ditemukan.")
        }
    }

    /**
     * Menampilkan seluruh buku di perpustakaan.
     */
    fun listAllBooks() {
        val books = bookLibrary.getAllBooks()
        if (books.isEmpty()) {
            println("📚 Tidak ada buku di perpustakaan.")
        } else {
            println("📚 Daftar Buku:")
            books.forEachIndexed { index, buku ->
                println("${index + 1}. ${buku.judul} - ${buku.penulis} (${buku.status})")
            }
        }
    }

    /**
     * Mencari buku berdasarkan judul.
     */
    fun findBook(title: String): Buku? {
        val book = bookLibrary.findBookByTitle(title)
        if (book != null) {
            println("🔍 Ditemukan: ${book.judul} oleh ${book.penulis}")
        } else {
            println("❌ Buku '$title' tidak ditemukan.")
        }
        return book
    }
}

fun main() {
    val book1 = Buku("Naruto", "Masashi Kishimoto")
    val book2 = Buku("Chainsaw Man", "Tatsuki Fujimoto")

    // Menggunakan Singleton LibraryManager
    LibraryManager.addBook(book1)
    LibraryManager.addBook(book2)

    LibraryManager.listAllBooks()
    LibraryManager.findBook("Naruto")

    LibraryManager.removeBook("Naruto")
    LibraryManager.listAllBooks()
}
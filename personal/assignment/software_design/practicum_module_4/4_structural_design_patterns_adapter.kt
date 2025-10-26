package com.example.externallibrary.api

import com.example.library.model.Buku
import com.example.library.service.BookLibrary

/**
 * Simulasi antarmuka sistem perpustakaan online eksternal.
 */
interface OnlineLibraryAPI {
    fun uploadBook(title: String, author: String)
    fun fetchBook(title: String): Map<String, String>?
    fun deleteBook(title: String): Boolean
}

class OnlineLibraryAPIImpl : OnlineLibraryAPI {
    private val remoteStorage = mutableMapOf<String, String>()

    override fun uploadBook(title: String, author: String) {
        remoteStorage[title.lowercase()] = author
        println("🌐 [API] Buku '$title' oleh $author telah diunggah ke server.")
    }

    override fun fetchBook(title: String): Map<String, String>? {
        val author = remoteStorage[title.lowercase()]
        return author?.let { mapOf("judul" to title, "penulis" to it) }
    }

    override fun deleteBook(title: String): Boolean {
        val removed = remoteStorage.remove(title.lowercase())
        return removed != null
    }
}

/**
 * Adapter untuk menghubungkan BookLibrary internal dengan sistem eksternal.
 */
class OnlineLibraryAdapter(
    private val onlineAPI: OnlineLibraryAPI
) : BookLibrary {

    private val localBooks = mutableListOf<Buku>()

    override fun addBook(book: Buku) {
        // Tambah ke sistem lokal
        localBooks.add(book)

        // Sinkronisasi dengan API eksternal
        onlineAPI.uploadBook(book.judul, book.penulis)
        println("🔗 [Adapter] Buku '${book.judul}' disinkronkan ke sistem online.")
    }

    override fun removeBook(title: String): Boolean {
        val removed = localBooks.removeIf { it.judul.equals(title, ignoreCase = true) }
        if (removed) {
            onlineAPI.deleteBook(title)
            println("🔗 [Adapter] Buku '$title' juga dihapus dari sistem online.")
        }
        return removed
    }

    override fun findBookByTitle(title: String): Buku? {
        // Coba cari di lokal
        val local = localBooks.find { it.judul.equals(title, ignoreCase = true) }
        if (local != null) return local

        // Jika tidak ada, ambil dari API eksternal
        val remote = onlineAPI.fetchBook(title)
        return remote?.let {
            Buku.Builder()
                .judul(it["judul"] ?: "")
                .penulis(it["penulis"] ?: "")
                .build()
        }
    }

    override fun getAllBooks(): List<Buku> = localBooks.toList()
}

fun main() {
    val onlineAPI = OnlineLibraryAPIImpl()
    val libraryAdapter = OnlineLibraryAdapter(onlineAPI)

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

    // Tambah buku
    libraryAdapter.addBook(naruto)
    libraryAdapter.addBook(chainsawMan)

    println("\n📚 Buku di sistem lokal:")
    libraryAdapter.getAllBooks().forEach { println(it) }

    println("\n🔍 Mencari buku 'Naruto' (jika tidak ada di lokal, ambil dari API):")
    println(libraryAdapter.findBookByTitle("Naruto"))
}

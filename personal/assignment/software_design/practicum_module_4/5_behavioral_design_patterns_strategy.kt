package com.example.booklibrary.strategy

import com.example.booklibrary.model.Buku

/**
 * Interface untuk strategi pencarian buku.
 */
interface SearchStrategy {
    fun search(books: List<Buku>, keyword: String): List<Buku>
}

class SearchByTitleStrategy : SearchStrategy {
    override fun search(books: List<Buku>, keyword: String): List<Buku> {
        return books.filter { it.judul.contains(keyword, ignoreCase = true) }
    }
}

class SearchByAuthorStrategy : SearchStrategy {
    override fun search(books: List<Buku>, keyword: String): List<Buku> {
        return books.filter { it.penulis.contains(keyword, ignoreCase = true) }
    }
}

class SearchByCategoryStrategy : SearchStrategy {
    override fun search(books: List<Buku>, keyword: String): List<Buku> {
        return books.filter { it.kategori?.contains(keyword, ignoreCase = true) == true }
    }
}

/**
 * Context untuk menjalankan strategi pencarian buku.
 */
class BookSearchContext(private var strategy: SearchStrategy) {

    fun setStrategy(strategy: SearchStrategy) {
        this.strategy = strategy
    }

    fun searchBooks(books: List<Buku>, keyword: String): List<Buku> {
        return strategy.search(books, keyword)
    }
}

fun main() {
    val books = listOf(
        Buku.Builder().judul("Naruto").penulis("Masashi Kishimoto").tahunTerbit(1999).build(),
        Buku.Builder().judul("Chainsaw Man").penulis("Tatsuki Fujimoto").tahunTerbit(2018).build(),
        Buku.Builder().judul("One Piece").penulis("Eiichiro Oda").tahunTerbit(1997).build()
    )

    val context = BookSearchContext(SearchByTitleStrategy())

    println("🔍 Cari berdasarkan judul 'Man':")
    context.searchBooks(books, "Man").forEach { println(" - ${it.judul} oleh ${it.penulis}") }

    println("\n🔍 Ubah strategi menjadi pencarian berdasarkan penulis:")
    context.setStrategy(SearchByAuthorStrategy())
    context.searchBooks(books, "Oda").forEach { println(" - ${it.judul} oleh ${it.penulis}") }
}

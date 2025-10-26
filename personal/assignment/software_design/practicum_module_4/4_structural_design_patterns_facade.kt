package com.example.librarysystem.service

import com.example.library.model.Buku

class BookService {
    private val daftarBuku = mutableListOf<Buku>()

    fun addBook(book: Buku) {
        daftarBuku.add(book)
        println("📚 Buku '${book.judul}' berhasil ditambahkan.")
    }

    fun findBook(title: String): Buku? {
        return daftarBuku.find { it.judul.equals(title, ignoreCase = true) }
    }

    fun listBooks(): List<Buku> = daftarBuku
}

class MemberService {
    private val daftarAnggota = mutableListOf<Anggota>()

    fun registerMember(member: Anggota) {
        daftarAnggota.add(member)
        println("👤 Anggota '${member.nama}' berhasil terdaftar.")
    }

    fun findMember(id: String): Anggota? {
        return daftarAnggota.find { it.idAnggota == id }
    }
}

class BorrowService {
    fun borrowBook(member: Anggota, book: Buku): Boolean {
        return if (book.status == "tersedia") {
            book.status = "dipinjam"
            println("📖 Buku '${book.judul}' dipinjam oleh ${member.nama}.")
            true
        } else {
            println("❌ Buku '${book.judul}' sedang dipinjam.")
            false
        }
    }

    fun returnBook(member: Anggota, book: Buku): Boolean {
        return if (book.status == "dipinjam") {
            book.status = "tersedia"
            println("✅ Buku '${book.judul}' dikembalikan oleh ${member.nama}.")
            true
        } else {
            println("⚠️ Buku '${book.judul}' belum pernah dipinjam.")
            false
        }
    }
}

/**
 * Pola Façade untuk menyederhanakan akses ke berbagai subsistem perpustakaan.
 */
class LibraryFacade {

    private val bookService = BookService()
    private val memberService = MemberService()
    private val borrowService = BorrowService()

    /**
     * Registrasi anggota baru ke sistem.
     */
    fun registerMember(id: String, name: String) {
        val member = Anggota(id, name)
        memberService.registerMember(member)
    }

    /**
     * Menambahkan buku baru ke koleksi perpustakaan.
     */
    fun addBook(title: String, author: String) {
        val book = Buku(title, author)
        bookService.addBook(book)
    }

    /**
     * Menampilkan semua buku yang tersedia.
     */
    fun showAvailableBooks() {
        val books = bookService.listBooks()
        if (books.isEmpty()) {
            println("📭 Tidak ada buku di perpustakaan.")
        } else {
            println("📚 Daftar Buku:")
            books.forEach { println("- ${it.judul} (${it.status})") }
        }
    }

    /**
     * Proses peminjaman buku oleh anggota.
     */
    fun borrowBook(memberId: String, title: String) {
        val member = memberService.findMember(memberId)
        val book = bookService.findBook(title)

        if (member != null && book != null) {
            borrowService.borrowBook(member, book)
        } else {
            println("❌ Gagal meminjam: anggota atau buku tidak ditemukan.")
        }
    }

    /**
     * Proses pengembalian buku oleh anggota.
     */
    fun returnBook(memberId: String, title: String) {
        val member = memberService.findMember(memberId)
        val book = bookService.findBook(title)

        if (member != null && book != null) {
            borrowService.returnBook(member, book)
        } else {
            println("❌ Gagal mengembalikan: anggota atau buku tidak ditemukan.")
        }
    }
}

fun main() {
    val library = LibraryFacade()

    library.registerMember("A001", "Mubarak Najib")
    library.addBook("Naruto", "Masashi Kishimoto")
    library.addBook("Chainsaw Man", "Tatsuki Fujimoto")

    println("\n📚 Daftar Buku:")
    library.showAvailableBooks()

    println("\n📖 Proses Peminjaman:")
    library.borrowBook("A001", "Naruto")

    println("\n📚 Setelah dipinjam:")
    library.showAvailableBooks()

    println("\n📗 Proses Pengembalian:")
    library.returnBook("A001", "Naruto")

    println("\n📚 Setelah dikembalikan:")
    library.showAvailableBooks()
}

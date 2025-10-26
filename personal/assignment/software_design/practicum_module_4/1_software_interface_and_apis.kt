package com.example.library

interface LibraryService {

    /**
     * Menambahkan buku baru ke dalam sistem perpustakaan.
     */
    fun addBook(book: Buku)

    /**
     * Mendaftarkan anggota baru ke perpustakaan.
     */
    fun registerMember(member: Anggota)

    /**
     * Mencari buku berdasarkan judul.
     * @return Buku jika ditemukan, null jika tidak ada.
     */
    fun findBookByTitle(title: String): Buku?

    /**
     * Meminjamkan buku kepada anggota tertentu.
     * @param member anggota yang meminjam
     * @param book buku yang ingin dipinjam
     * @return true jika peminjaman berhasil, false jika gagal
     */
    fun borrowBook(member: Anggota, book: Buku): Boolean

    /**
     * Mengembalikan buku yang telah dipinjam oleh anggota.
     * @param member anggota yang mengembalikan buku
     * @param book buku yang dikembalikan
     * @return true jika pengembalian berhasil, false jika gagal
     */
    fun returnBook(member: Anggota, book: Buku): Boolean
}

class Perpustakaan : LibraryService {

    private val daftarBuku = mutableListOf<Buku>()
    private val daftarAnggota = mutableListOf<Anggota>()

    override fun addBook(book: Buku) {
        daftarBuku.add(book)
    }

    override fun registerMember(member: Anggota) {
        daftarAnggota.add(member)
    }

    override fun findBookByTitle(title: String): Buku? {
        return daftarBuku.find { it.judul.equals(title, ignoreCase = true) }
    }

    override fun borrowBook(member: Anggota, book: Buku): Boolean {
        val targetBook = daftarBuku.find { it == book && it.status == "tersedia" }
        return if (targetBook != null) {
            targetBook.status = "dipinjam"
            true
        } else {
            false
        }
    }

    override fun returnBook(member: Anggota, book: Buku): Boolean {
        val targetBook = daftarBuku.find { it == book && it.status == "dipinjam" }
        return if (targetBook != null) {
            targetBook.status = "tersedia"
            true
        } else {
            false
        }
    }
}

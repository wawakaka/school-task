package com.example.booklibrary.observer

import com.example.booklibrary.model.Buku

/**
 * Interface observer untuk menerima notifikasi perubahan status buku.
 */
interface BookObserver {
    fun onBookStatusChanged(buku: Buku, oldStatus: String, newStatus: String)
}

/**
 * Buku yang dapat dipantau (Observable) untuk perubahan status.
 */
class ObservableBuku private constructor(
    val judul: String,
    val penulis: String,
    val tahunTerbit: Int?,
    statusAwal: String
) {
    private val observers = mutableListOf<BookObserver>()

    var status: String = statusAwal
        set(value) {
            val oldStatus = field
            field = value
            notifyObservers(oldStatus, value)
        }

    fun addObserver(observer: BookObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: BookObserver) {
        observers.remove(observer)
    }

    private fun notifyObservers(oldStatus: String, newStatus: String) {
        observers.forEach { it.onBookStatusChanged(this, oldStatus, newStatus) }
    }

    override fun toString(): String {
        return "Buku(judul='$judul', penulis='$penulis', tahunTerbit=$tahunTerbit, status='$status')"
    }

    // Builder pattern tetap digunakan untuk fleksibilitas
    class Builder {
        private var judul: String = ""
        private var penulis: String = ""
        private var tahunTerbit: Int? = null
        private var status: String = "tersedia"

        fun judul(judul: String) = apply { this.judul = judul }
        fun penulis(penulis: String) = apply { this.penulis = penulis }
        fun tahunTerbit(tahunTerbit: Int) = apply { this.tahunTerbit = tahunTerbit }
        fun status(status: String) = apply { this.status = status }

        fun build(): ObservableBuku {
            require(judul.isNotBlank()) { "Judul buku tidak boleh kosong." }
            require(penulis.isNotBlank()) { "Penulis buku tidak boleh kosong." }
            return ObservableBuku(judul, penulis, tahunTerbit, status)
        }
    }
}

/**
 * Observer untuk mengirim notifikasi ketika status buku berubah.
 */
class NotificationService : BookObserver {
    override fun onBookStatusChanged(buku: Buku, oldStatus: String, newStatus: String) {
        println("🔔 Notifikasi: Buku '${buku.judul}' berubah dari '$oldStatus' menjadi '$newStatus'")
    }
}

fun main() {
    val notificationService = NotificationService()

    val naruto = ObservableBuku.Builder()
        .judul("Naruto")
        .penulis("Masashi Kishimoto")
        .tahunTerbit(1999)
        .build()

    naruto.addObserver(notificationService)

    // Simulasi perubahan status buku
    naruto.status = "dipinjam"
    naruto.status = "tersedia"
}

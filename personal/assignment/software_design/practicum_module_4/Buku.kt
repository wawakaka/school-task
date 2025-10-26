package com.example.library.model

data class Buku(
    val judul: String,
    val penulis: String,
    var status: String = "tersedia"
)
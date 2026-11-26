package com.example.navigationlab.model

data class Book(
    override val id: String,
    val title: String,
    val pages: Int,
    val author: Author,
) : LibraryItem

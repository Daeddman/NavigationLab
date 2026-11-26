package com.example.navigationlab.model

data class Author(
    override val id: String,
    val name: String,
    val description: String,
) : LibraryItem

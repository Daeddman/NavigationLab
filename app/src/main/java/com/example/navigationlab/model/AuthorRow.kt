package com.example.navigationlab.model

data class AuthorRow(
    override val id: String,
    val title: String,
    val authors: List<Author>,
) : LibraryItem

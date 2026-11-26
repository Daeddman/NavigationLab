package com.example.navigationlab.repository

import com.example.navigationlab.model.LibraryItem

interface LibraryRepository {
    fun getItems(): List<LibraryItem>
}

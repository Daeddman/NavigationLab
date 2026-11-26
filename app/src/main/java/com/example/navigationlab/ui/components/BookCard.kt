package com.example.navigationlab.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navigationlab.model.Book

@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("Книга", style = MaterialTheme.typography.labelLarge)
            Text(book.title, style = MaterialTheme.typography.titleMedium)
            Text("Автор: ${book.author.name}")
            Text("Сторінок: ${book.pages}")
        }
    }
}

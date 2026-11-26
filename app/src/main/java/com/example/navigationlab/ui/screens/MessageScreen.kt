package com.example.navigationlab.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.navigationlab.model.Author
import com.example.navigationlab.model.AuthorRow
import com.example.navigationlab.model.Book
import com.example.navigationlab.model.LibraryItem
import com.example.navigationlab.ui.components.AuthorCard
import com.example.navigationlab.ui.components.AuthorRowCard
import com.example.navigationlab.ui.components.BookCard

@Composable
fun MessageScreen(
    changed: Boolean,
    libraryItems: List<LibraryItem>,
    onChange: () -> Unit,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = if (changed) "Гарного дня!" else "Привіт!",
            style = MaterialTheme.typography.titleLarge,
        )
        Text("Елементів у бібліотеці: ${libraryItems.size}", style = MaterialTheme.typography.titleMedium)
        // weight(1f) віддає списку місце, що залишилось між заголовками і кнопкою.
        // Не додаємо verticalScroll до зовнішнього Column.
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f).testTag("library_list"),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = libraryItems,
                key = { item -> item.id },
                contentType = { item ->
                    when (item) {
                        is Author -> "author"
                        is Book -> "book"
                        is AuthorRow -> "author_row"
                    }
                },
            ) { item ->
                when (item) {
                    is Author -> AuthorCard(item, Modifier.fillMaxWidth())
                    is Book -> BookCard(item, Modifier.fillMaxWidth())
                    is AuthorRow -> AuthorRowCard(item, Modifier.fillMaxWidth())
                }
            }
        }
        OutlinedButton(
            onClick = onChange,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
        ) {
            Text("Змінити повідомлення")
        }
    }
}

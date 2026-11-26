package com.example.navigationlab.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.navigationlab.model.AuthorRow

@Composable
fun AuthorRowCard(row: AuthorRow, modifier: Modifier = Modifier) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(row.title, style = MaterialTheme.typography.titleMedium)
        // Горизонтальна прокрутка всередині вертикальної не потребує verticalScroll.
        LazyRow(
            modifier = Modifier.fillMaxWidth().testTag("authors_row_${row.id}"),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = row.authors, key = { it.id }) { author ->
                AuthorCard(author = author, modifier = Modifier.width(220.dp))
            }
        }
    }
}

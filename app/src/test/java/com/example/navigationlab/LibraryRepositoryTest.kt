package com.example.navigationlab

import com.example.navigationlab.model.Author
import com.example.navigationlab.model.AuthorRow
import com.example.navigationlab.model.Book
import com.example.navigationlab.navigation.AppDestinations
import com.example.navigationlab.repository.DemoLibraryRepository
import com.example.navigationlab.viewmodel.LabViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

class LibraryRepositoryTest {
    @Test
    fun demoDataContainsBothModelsAndUniqueStableKeys() {
        val repository = DemoLibraryRepository()
        val items = repository.getItems()
        assertTrue(items.any { it is Author })
        assertTrue(items.any { it is Book })
        assertTrue(items.any { it is AuthorRow })
        assertEquals(items.size, items.map { it.id }.distinct().size)
        assertEquals(items.map { it.id }, repository.getItems().map { it.id })
        val authors = items.filterIsInstance<Author>()
        items.filterIsInstance<Book>().forEach { book ->
            assertTrue(authors.contains(book.author))
            assertTrue(book.pages > 0)
        }
        val row = items.filterIsInstance<AuthorRow>().single()
        assertEquals(row.authors.size, row.authors.map { it.id }.distinct().size)
    }

    @Test
    fun otherActionsKeepLoadedListAndExistingLabBehavior() {
        val model = LabViewModel()
        val list = model.state.value.libraryItems
        model.incrementCounter()
        model.toggleMessage()
        model.addPoints()
        model.selectDestination(AppDestinations.MESSAGE)
        val state = model.state.value
        assertSame(list, state.libraryItems)
        assertEquals(1, state.count)
        assertTrue(state.messageChanged)
        assertEquals(5, state.score)
        assertEquals(AppDestinations.MESSAGE, state.destination)
    }
}

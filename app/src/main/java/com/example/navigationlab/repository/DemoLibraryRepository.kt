package com.example.navigationlab.repository

import com.example.navigationlab.model.Author
import com.example.navigationlab.model.AuthorRow
import com.example.navigationlab.model.Book
import com.example.navigationlab.model.LibraryItem

class DemoLibraryRepository : LibraryRepository {
    override fun getItems(): List<LibraryItem> {
        val authors = listOf(
            Author("author_1", "Леся Українка", "Поезія та драматургія"),
            Author("author_2", "Іван Франко", "Проза, поезія та публіцистика"),
            Author("author_3", "Тарас Шевченко", "Поезія та малярство"),
            Author("author_4", "Михайло Коцюбинський", "Українська проза"),
            Author("author_5", "Ольга Кобилянська", "Проза та новелістика"),
        )
        // Кількість сторінок — умовні тестові дані, не опис конкретних видань.
        val titles = listOf(
            "Лісова пісня", "Захар Беркут", "Кобзар", "Тіні забутих предків", "Земля",
            "Бояриня", "Мойсей", "Гайдамаки", "Intermezzo", "Людина",
            "Камінний господар", "Перехресні стежки", "Катерина", "Fata morgana", "Царівна",
        )
        val books = titles.mapIndexed { index, title ->
            Book(
                id = "book_$index",
                title = title,
                pages = 100 + index * 12,
                author = authors[index % authors.size],
            )
        }
        return buildList {
            add(authors[0])
            add(books[0])
            add(AuthorRow("row_featured", "Добірка авторів", authors))
            books.drop(1).forEachIndexed { index, book ->
                add(book)
                if (index < authors.size - 1) add(authors[index + 1])
            }
        }
    }
}

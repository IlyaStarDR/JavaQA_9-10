package com.epam.homework8.init;

import com.epam.homework8.books.Book;
import com.epam.homework8.books.Books;

public final class InitBook {

    public static Books initBooks() {

        Books books = new Books(0);
        books.add(new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 200));
        books.add(new Book(2, "Cтарик и Mоре", "Эрнест Хемингуэй", "Charles Scribner's Sons", 1936, 98, 145));
        books.add(new Book(3, "1984", "Джордж Оруэлл", "ACT", 1948, 250, 435));
        books.add(new Book(4, "Harry Potter»", "Джоан Роулинг", "Bloomsbury Publishing", 1997, 402, 120));
        books.add(new Book(5, "Идиот", "Фёдор Достоевский", "ACT", 1868, 800, 183.6));
        books.add(new Book(6, "Три товарища", "Эрих Мария Ремар", "AC", 1937, 480, 14));
        books.add(new Book(7, "Три товарища", "Эрих Мария Ремарк", "ACT", 1936, 480, 142));
        books.add(new Book(8, "Три товарища", "Эрих Мария Ремарк", "ACT", 1936, 480, 142));
        return books;
    }
}

package com.epam.homework8.comparator;

import com.epam.homework8.books.Book;

import java.util.Comparator;

public class NameComparator implements Comparator<Book> {

    @Override
    public int compare(Book o1, Book o2) {
        if (o1 == null) {
            return (o2 == null) ? 0 : -1;
        }
        if (o2 == null) {
            return 1;
        }
        return o1.getAuthor().compareTo(o2.getAuthor());
    }
}

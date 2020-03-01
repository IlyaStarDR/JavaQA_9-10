package com.epam.homework8.validator;

import com.epam.homework8.books.Book;

public class Validator {
    public static boolean isEmpty(Book[] listOfBooks) {
        int counter = 0;
        for (Book element : listOfBooks) {
            if (element == null) {
                counter++;
            }
        }
        return counter == listOfBooks.length;
    }
}

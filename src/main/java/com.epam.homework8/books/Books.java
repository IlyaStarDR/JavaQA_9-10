package com.epam.homework8.books;

import com.epam.homework8.exception.EmptyArrayRuntimeException;
import com.epam.homework8.validator.Validator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;


public class Books implements Cloneable, Serializable {
    private Book[] listOfBooks;
    private int counter;


    public Books(int length) {
        listOfBooks = new Book[length];
    }

    public void viewAll() throws EmptyArrayRuntimeException {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        }
        for (Book element : listOfBooks) {
            if (element != null) {
                System.out.println(element.toString());
            }
        }
    }

    public Book getBook(int index) {
        for (int i = 0; i < listOfBooks.length; i++) {
            if (i == index){
                return listOfBooks[i];
            }
        }
        throw new EmptyArrayRuntimeException("No such element");
    }

    public void add(Book book) {
        try {
            listOfBooks[counter] = book;
            counter++;
        } catch (IndexOutOfBoundsException e) {
            listOfBooks = Arrays.copyOf(listOfBooks, (int) ((listOfBooks.length + 1) * 1.5));
            add(book);
        }
    }


    public void changePrice(double percent, char plusOrMinus) {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        } else if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Illegal percent");
        }
        workWithPrice(percent, plusOrMinus);
    }

    public Books searchAuthor(String author) {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        }
        return getBooks(author);
    }

    private void workWithPrice(double percent, char plusOrMinus) {
        for (Book element : listOfBooks) {
            if (element != null) {
                switch (plusOrMinus) {
                    case '+':
                        element.setPrice(element.getPrice() + element.getPrice() * percent / 100);
                        break;
                    case '-':
                        element.setPrice(element.getPrice() - element.getPrice() * percent / 100);
                        break;
                }
            }
        }
    }

    private Books getBooks(String author) {
        Books books = new Books(listOfBooks.length);
        for (Book element : listOfBooks) {
            if (element != null && element.getAuthor().equals(author)) {
                books.add(element);
            }
        }
        return books;
    }

    public Books searchYear(int year) {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        }
        return getYearOfBook(year);
    }

    private Books getYearOfBook(int year) {
        Books books = new Books(listOfBooks.length);
        for (Book element : listOfBooks) {
            if (element != null && element.getYearOfPublishing() > year && year > 0) {
                books.add(element);
            } else {
                throw new IllegalArgumentException("Illegal year");
            }
        }
        return books;
    }

    @Override
    public Books clone() throws CloneNotSupportedException {
        Books clone = (Books) super.clone();
        clone.listOfBooks = Arrays.copyOf(listOfBooks, listOfBooks.length);
        return clone;
    }

    //    public void getNameOfAuthorCompared() {
//        if (Validator.isEmpty(listOfBooks)) {
//            throw new EmptyArrayRuntimeException("Bookshelf is empty");
//        }
//        Arrays.sort(listOfBooks, new NameComparator());
//    }
    public void getNameOfAuthorCompared() {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        }
        Arrays.sort(listOfBooks, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 == null) {
                            return (o2 == null) ? 0 : -1;
                        }
                        if (o2 == null) {
                            return 1;
                        }
                        return ((Book) o1).getAuthor().compareTo(((Book) o2).getAuthor());
                    }
                }
        );
    }
//    public void getPublishingHouseCompared() {
//        if (Validator.isEmpty(listOfBooks)) {
//            throw new EmptyArrayRuntimeException("Bookshelf is empty");
//        }
//        Arrays.sort(listOfBooks, new PublisherComparator());
//    }

    public void getPublishingHouseCompared() {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        }
        Arrays.sort(listOfBooks, new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        if (o1 == null) {
                            return (o2 == null) ? 0 : -1;
                        }
                        if (o2 == null) {
                            return 1;
                        }
                        return ((Book) o1).getPublishingHouse().compareTo(((Book) o2).getPublishingHouse());
                    }
                }
        );
    }

    //    public void getPriceCompared() {
//        if (Validator.isEmpty(listOfBooks)) {
//            throw new EmptyArrayRuntimeException("Bookshelf is empty");
//        }
//        Arrays.sort(listOfBooks, new PriceComparator());
//    }
    public void getPriceCompared() {
        if (Validator.isEmpty(listOfBooks)) {
            throw new EmptyArrayRuntimeException("Bookshelf is empty");
        }
        Arrays.sort(listOfBooks, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 == null) {
                    return (o2 == null) ? 0 : -1;
                }
                if (o2 == null) {
                    return 1;
                }
                return (int) ((Book) o2).getPrice() - (int) ((Book) o1).getPrice();
            }
        });
    }
}
import com.epam.homework8.books.Book;
import com.epam.homework8.books.Books;
import com.epam.homework8.exception.EmptyArrayRuntimeException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BooksTest {
    @Parameterized.Parameters
    public static Collection<Book[]> data() {
        return Arrays.asList(new Book[][] {
                {
                        new Book(3, "1984", "Джордж Оруэлл", "BACT", 1948, 250, 100),
                        new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400)
                }
        });
    }
    private Book book1;
    private Book book2;

    public BooksTest(Book book1, Book book2) {
        this.book1 = book1;
        this.book2 = book2;
    }

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testViewAllIfArrayIsEmpty() {
        Books books = new Books(10);
        books.viewAll();
    }

    @Test
    public void testGetBookByExistingIndex() {
        Books books = new Books(2);
        books.add(book1);
        books.add(book2);
        Assert.assertEquals(book2, books.getBook(1));
    }

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testGetBookByNotExistingIndex() {
        Books books = new Books(2);
        books.add(book1);
        books.getBook(10);
    }

    @Test
    public void testAddBookArrayIsNotFull() {
        Books books = new Books(1);
        books.add(book2);
        Assert.assertEquals(book2, books.getBook(0));
    }

    @Test
    public void testAddBookArrayIsFull() {
        Books books = new Books(0);
        books.add(book1);
        Assert.assertEquals(book1, books.getBook(0));
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testAddBookArrayIsNegative() {
        Books books = new Books(-1);
        books.add(book2);
        Assert.assertEquals(book2, books.getBook(0));
    }


    @Test(expected = EmptyArrayRuntimeException.class)
    public void changePriceBookIsEmpty() {
        Books books = new Books(0);
        books.changePrice(25, '+');
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePriceBookPercentIsLessThanZero() {
        Books books = new Books(1);
        books.add(book2);
        books.changePrice(-25, '+');
    }

    @Test
    public void changePriceBookPercentIsZero() throws CloneNotSupportedException {
        Books books = new Books(1);
        books.add(book2);
        book2.setPrice(400);
        books.clone().changePrice(0, '+');
        System.out.println(books.getBook(0).getPrice());
        Assert.assertEquals(400, books.getBook(0).getPrice(), 0);
    }

    @Test
    public void changePriceBookPercentIsOneHundredDown() {
        Books books = new Books(1);
        books.add(book2);
        books.changePrice(100, '-');
        Assert.assertEquals(0, books.getBook(0).getPrice(), 0);
    }

    @Test
    public void changePriceBookPercentIsFiftyUp() {
        Books books = new Books(1);
        books.add(book2);
        books.changePrice(50, '+');
        Assert.assertEquals(600, books.getBook(0).getPrice(), 0);
    }

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testSearchAuthorArrayIsEmpty() {
        Books books = new Books(0);
        books.searchAuthor("Лев Давидович Троцкий");
    }

    @Test
    public void testSearchAuthorExistingInArray() {
        Books books = new Books(1);
        books.add(book2);
        String expectedResult = books.searchAuthor("Лев Давидович Троцкий").getBook(0).getAuthor();
        Assert.assertEquals(expectedResult, "Лев Давидович Троцкий");
    }

    @Test
    public void testSearchAuthorNotExistingInArray() {
        Books books = new Books(1);
        books.add(book2);
        Books expectedResult = books.searchAuthor("Эрнест Хемингуэй");
        Assert.assertNull(expectedResult.getBook(0));
    }

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testSearchYearArrayIsEmpty() {
        Books books = new Books(0);
        books.searchYear(1934);
    }

    @Test
    public void testSearchYearExistingInArray() {
        Books books = new Books(1);
        books.add(book2);
        int expectedResult = books.searchYear(1900).getBook(0).getYearOfPublishing();
        Assert.assertEquals(expectedResult, 1936);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchYearNotExistingInArray() {
        Books books = new Books(1);
        books.add(book2);
        books.searchYear(1940);
    }

    @Test
    public void testViewAllIfArrayAndArrayClonedAreNotEquals() throws CloneNotSupportedException {
        Books books = new Books(0);
        Assert.assertNotEquals(books, books.clone());
    }

    @Test
    public void testViewAllIfArraysClonedAreNotEquals() throws CloneNotSupportedException {
        Books books = new Books(0);
        Assert.assertNotEquals(books.clone(), books.clone());
    }

    @Test
    public void testGetPriceComparedArrayIsSortedByPrice() {
        Books books = new Books(2);
        books.add(book1);
        books.add(book2);
        books.getPriceCompared();
        Assert.assertEquals(book2, books.getBook(0));
    }

    @Test
    public void testGetNameOfAuthorComparedArrayIsSortedByAuthor() {
        Books books = new Books(2);
        books.add(book2);
        books.add(book1);
        books.getNameOfAuthorCompared();
        Assert.assertEquals(book1, books.getBook(0));
    }

    @Test
    public void testGetPublishingHouseComparedArrayIsSortedByPublishingHouse() {
        Books books = new Books(2);
        books.add(book1);
        books.add(book2);
        books.getPublishingHouseCompared();
        Assert.assertEquals(book2, books.getBook(0));
    }
}

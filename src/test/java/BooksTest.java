import com.epam.homework8.books.Book;
import com.epam.homework8.books.Books;
import com.epam.homework8.exception.EmptyArrayRuntimeException;
import org.junit.Assert;
import org.junit.Test;

public class BooksTest {

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testViewAllIfArrayIsEmpty() {
        Books book = new Books(10);
        book.viewAll();
    }

    @Test
    public void testGetBookByExistingIndex() {
        Books books1 = new Books(2);
        books1.add(new Book(3, "1984", "Джордж Оруэлл", "ACT", 1948, 250, 100));
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        Assert.assertEquals(book, books1.getBook(1));
    }

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testGetBookByNotExistingIndex() {
        Books books1 = new Books(2);
        books1.add(new Book(3, "1984", "Джордж Оруэлл", "ACT", 1948, 250, 100));
        books1.getBook(10);
    }

    @Test
    public void testAddBookArrayIsNotFull() {
        Books books1 = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        Assert.assertEquals(book, books1.getBook(0));
    }

    @Test
    public void testAddBookArrayIsFull() {
        Books books1 = new Books(0);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        Assert.assertEquals(book, books1.getBook(0));
    }

    @Test(expected = NegativeArraySizeException.class)
    public void testAddBookArrayIsNegative() {
        Books books1 = new Books(-1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        Assert.assertEquals(book, books1.getBook(0));
    }


    @Test(expected = EmptyArrayRuntimeException.class)
    public void changePriceBookIsEmpty() {
        Books books1 = new Books(0);
        books1.changePrice(25, '+');
    }

    @Test(expected = IllegalArgumentException.class)
    public void changePriceBookPercentIsLessThanZero() {
        Books books1 = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        books1.changePrice(-25, '+');
    }

    @Test
    public void changePriceBookPercentIsZero() {
        Books books1 = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        books1.changePrice(0, '+');
        Assert.assertEquals(400, books1.getBook(0).getPrice(), 0);

    }

    @Test
    public void changePriceBookPercentIsOneHundredDown() {
        Books books1 = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        books1.changePrice(100, '-');
        Assert.assertEquals(0, books1.getBook(0).getPrice(), 0);
    }

    @Test
    public void changePriceBookPercentIsFiftyUp() {
        Books books1 = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(book);
        books1.changePrice(50, '+');
        Assert.assertEquals(600, books1.getBook(0).getPrice(), 0);
    }

    @Test(expected = EmptyArrayRuntimeException.class)
    public void testSearchAuthorArrayIsEmpty() {
        Books books = new Books(0);
        books.searchAuthor("Лев Давидович Троцкий");
    }

    @Test
    public void testSearchAuthorExistingInArray() {
        Books books = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books.add(book);
        String expectedResult = books.searchAuthor("Лев Давидович Троцкий").getBook(0).getAuthor();
        Assert.assertEquals(expectedResult, "Лев Давидович Троцкий");
    }

    @Test
    public void testSearchAuthorNotExistingInArray() {
        Books books = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books.add(book);
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
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books.add(book);
        int expectedResult = books.searchYear(1900).getBook(0).getYearOfPublishing();
        Assert.assertEquals(expectedResult, 1936);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchYearNotExistingInArray() {
        Books books = new Books(1);
        Book book = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books.add(book);
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
        Books books1 = new Books(3);
        books1.add(new Book(3, "1984", "Джордж Оруэлл", "ACT", 1948, 250, 100));
        Book expectedResult = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(expectedResult);
        books1.add(new Book(2, "Cтарик и Mоре", "Эрнест Хемингуэй", "Charles Scribner's Sons", 1936, 98, 200));
        books1.getPriceCompared();
        Assert.assertEquals(expectedResult, books1.getBook(0));
    }

    @Test
    public void testGetNameOfAuthorComparedArrayIsSortedByAuthor() {
        Books books1 = new Books(3);
        books1.add(new Book(2, "Cтарик и Mоре", "Эрнест Хемингуэй", "Charles Scribner's Sons", 1936, 98, 200));
        books1.add(new Book(3, "1984", "Джордж Оруэлл", "ACT", 1948, 250, 100));
        Book expectedResult = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(expectedResult);
        books1.getNameOfAuthorCompared();
        Assert.assertEquals(expectedResult, books1.getBook(1));
    }

    @Test
    public void testGetPublishingHouseComparedArrayIsSortedByPublishingHouse() {
        Books books1 = new Books(3);
        books1.add(new Book(2, "Cтарик и Mоре", "Эрнест Хемингуэй", "Charles Scribner's Sons", 1936, 98, 200));
        books1.add(new Book(3, "1984", "Джордж Оруэлл", "CT", 1948, 250, 100));
        Book expectedResult = new Book(1, "Преданная революция", "Лев Давидович Троцкий", "ACT", 1936, 189, 400);
        books1.add(expectedResult);
        books1.getPublishingHouseCompared();
        Assert.assertEquals(expectedResult, books1.getBook(0));
    }
}

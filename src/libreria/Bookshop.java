package libreria;

import java.util.List;
import java.util.stream.Collectors;

public class Bookshop {
    private final List<Book> bookList;

    public Bookshop(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int findBooksByAuthorWithPriceHigherThan(String author, double price) {
        return (int) bookList.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .filter(book -> book.getPrice() > price)
                .count();
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookList.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}

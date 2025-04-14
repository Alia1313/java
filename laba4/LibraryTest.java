package laba4;

import java.util.*;

public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("книга1", "автор1", 2001);
        Book book2 = new Book("книга2", "автор2", 2002);
        Book book3 = new Book("книга3", "автор3", 2002);
        Book book4 = new Book("книга4", "автор3", 2001);
        Book book5 = new Book("книга5", "автор1", 2003);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.printAllBooks();
        library.printUniqueAuthors();
        library.printAuthorStatistics();
        System.out.println("Книги автора1:");
        List<Book> autho1Books = library.findBooksByAuthor("автор1");
        for (Book book : autho1Books) {
            System.out.println(book);
        }
        System.out.println("Книги 2001 года:");
        List<Book> books2001 = library.findBooksByYear(2001);
        for (Book book : books2001) {
            System.out.println(book);
        }
        library.removeBook(book4);
        System.out.println("После удаления книги4:");
        library.printAllBooks();
        library.printUniqueAuthors();
        library.printAuthorStatistics();
    }
}

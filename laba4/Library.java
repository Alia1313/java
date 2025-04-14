package laba4;

import java.util.*;

class Library {
    private List<Book> books = new ArrayList<>();
    private Set<String> authors = new HashSet<>();
    private Map<String, Integer> authorStatistics = new HashMap<>();

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        authorStatistics.put(book.getAuthor(), authorStatistics.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            int count = authorStatistics.get(book.getAuthor()) - 1;
            if (count == 0) {
                authors.remove(book.getAuthor());
                authorStatistics.remove(book.getAuthor());
            } else {
                authorStatistics.put(book.getAuthor(), count);
            }
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(author)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYear() == year) {
                result.add(book);
            }
        }
        return result;
    }

    public void printAllBooks() {
        System.out.println("Все книги в библиотеке:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void printUniqueAuthors() {
        System.out.println("Уникальные авторы:");
        for (String author : authors) {
            System.out.println(author);
        }
    }

    public void printAuthorStatistics() {
        System.out.println("Статистика по авторам:");
        for (Map.Entry<String, Integer> entry : authorStatistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

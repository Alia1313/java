package laba4;

import java.util.*;

class Book {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }

    public boolean equals(Book book) {
        return year == book.year && this.title.equals(book.title) && this.author.equals(book.author);
    }

    public int hashCode() {
        return Objects.hash(title, author, year);
    }
}

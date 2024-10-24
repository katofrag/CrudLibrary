package ru.katofrag.library.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Укажите навание книги")
    @Size(min = 1, max = 100, message = "Название книги должно иметь длинну от 1 до 100 символов")
    private String bookName;

    @NotEmpty(message = "Укажите имя автора")
    @Size(min = 1, max = 50, message = "Имя автора должно иметь длинну от 1 до 50 символов")
    private String author;

    @Min(value = 0, message = "Год написания книги не может быть отрицательным")
    private int yearOfProduction;

    public Book(String bookName, String author, int yearOfProduction) {
        this.bookName = bookName;
        this.author = author;
        this.yearOfProduction = yearOfProduction;;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}



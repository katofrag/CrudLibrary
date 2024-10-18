package ru.katofrag.library.models;

public class Book {

    private int id;

    private String bookName;
    private String author;
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



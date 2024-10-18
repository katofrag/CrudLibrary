package ru.katofrag.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.katofrag.library.models.Book;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BookMapper());
    }

    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE book_id=?", new BookMapper(),
                id).stream().findAny().orElse(null);
    }

    public void save(Book saveBook) {
        jdbcTemplate.update("INSERT INTO Books(book_name,author,year_of_production) VALUES(?, ?, ?)",
                saveBook.getBookName(), saveBook.getAuthor(), saveBook.getYearOfProduction());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE Books SET book_name=?, author=?, year_of_production=? WHERE book_id=?",
                updatedBook.getBookName(), updatedBook.getAuthor(), updatedBook.getYearOfProduction(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Books WHERE book_id=?", id);
    }
}

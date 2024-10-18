package ru.katofrag.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.katofrag.library.models.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("book_id"));
        book.setBookName(rs.getString("book_name"));
        book.setAuthor(rs.getString("author"));
        book.setYearOfProduction(rs.getInt("year_of_production"));

        return book;
    }
}

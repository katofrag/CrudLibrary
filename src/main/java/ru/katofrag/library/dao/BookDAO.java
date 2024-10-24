package ru.katofrag.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.katofrag.library.models.Book;
import ru.katofrag.library.models.Person;

import java.util.List;
import java.util.Optional;

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
    // Назначает книгу человеку (этот метод вызывается, когда человек забирает книгу из библиотеки)
    public void assign(int id, Person selectPerson) {
        jdbcTemplate.update("UPDATE Books SET people_id=? WHERE book_id=?", selectPerson.getId(), id);

    }

    // Освбождает книгу (этот метод вызывается, когда человек возвращает книгу в библиотеку)
    public void release(int id) {
        jdbcTemplate.update("UPDATE Books SET people_id=NULL WHERE book_id=?", id);
    }

    // Join'им таблицы Books и People и получаем человека, которому принадлежит книга с указанным id
    public Optional<Person> getBookOwner(int id) {
        // Выбираем все колонки таблицы People из объединенной таблицы
        return jdbcTemplate.query("SELECT People.* FROM Books JOIN People ON Books.people_id = People.people_id " +
                        "WHERE Books.book_id = ?", new PersonMapper(), id).stream().findAny();
    }
}

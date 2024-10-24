package ru.katofrag.library.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.katofrag.library.models.Book;
import ru.katofrag.library.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM People", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM People WHERE people_id=?",
                new PersonMapper(), id).stream().findAny().orElse(null);
    }
    // Для валидации уникальности ФИО
    public Optional <Person> show(String name) {
        return jdbcTemplate.query("SELECT * FROM People WHERE name=?", new PersonMapper(), name).stream().findAny();
    }

    public void save(Person savePeople) {
        jdbcTemplate.update("INSERT INTO People(name, age) VALUES(?, ?)", savePeople.getName(),
                savePeople.getAge());
    }

    public void update(int id, Person updatedPeople) {
        jdbcTemplate.update("UPDATE People SET name=?, age=? WHERE people_id=?", updatedPeople.getName(),
                updatedPeople.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM People WHERE people_id=?", id);
    }

    // Здесь Join не нужен. И так уже получили человека с помощью отдельного метода
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE people_id = ?",
                new BookMapper(), id);
    }
}
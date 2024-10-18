package ru.katofrag.library.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.katofrag.library.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {

        Person person = new Person();

        person.setId(rs.getInt("people_id"));
        person.setName(rs.getString("name"));
        person.setAge(rs.getInt("age"));

        return person;
    }
}

package ru.katofrag.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.katofrag.library.dao.PersonDAO;
import ru.katofrag.library.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if(personDAO.show(person.getName()).isPresent()){
            // isPresent это проверка на null только современней и когда мы используем Optional
            // класс обертку в нашем случае в методе Show в классе PersonDAO
            errors.rejectValue("name", "", "Читатель с таким именем уже существует");
        }

        // Посмотреть есть ли человек с таким же email в БД
    }
}

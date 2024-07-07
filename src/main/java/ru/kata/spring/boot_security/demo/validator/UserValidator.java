package ru.kata.spring.boot_security.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServiceImpl;

@Component
public class UserValidator implements Validator {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserValidator(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(user.getRoles().size() == 0) {
            errors.rejectValue("name", "", "Выберите хотябы одну роль");
        }
        try {
            userDetailsService.loadUserByUsername(user.getEmail());
        } catch (Exception ignored) {
            return;
        }

        errors.rejectValue("name", "", "Человек с таким именем пользователя существует");
    }
}

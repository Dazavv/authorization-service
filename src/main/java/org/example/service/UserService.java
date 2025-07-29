package org.example.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final List<User> users;

    public UserService() {
        this.users = List.of(
                new User("anton", "1234", "Антон", "Иванов", "ivanov@mail.ru", Collections.singleton(Role.USER)),
                new User("ivan", "12345", "Сергей", "Петров", "petrov@email.com", Collections.singleton(Role.ADMIN))
        );
    }

    public Optional<User> getByLogin(@NonNull String login) {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst();
    }
}

package org.example.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.example.model.Role;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> getByLogin(@NonNull String login) {
        return userRepository.findByLogin(login);
    }

    public boolean checkExistedUser(String login, String email) {
        return userRepository.existsByLogin(login) || userRepository.existsByEmail(email);
    }

    public void saveNewUser(User user) {
        userRepository.save(user);
    }

    public void addNewRole(User user, Role role) {
        user.getRoles().add(role);
        userRepository.save(user);
    }
}

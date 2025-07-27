package com.portfolio.squadmate.service;

import com.portfolio.squadmate.domain.Coach;
import com.portfolio.squadmate.domain.User;
import com.portfolio.squadmate.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@Transactional
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createNewUser(String firstName, String lastName,
                              String username, String password, LocalDate birthDate, String role) {
        User newUser = RoleManagementFactory.getUser(role);

        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(username);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
        newUser.setBirthDate(birthDate);

        return userRepository.save(newUser);
    }
}

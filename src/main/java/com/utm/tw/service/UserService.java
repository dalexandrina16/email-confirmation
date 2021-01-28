package com.utm.tw.service;

import com.utm.tw.dao.RegistrationRepository;
import com.utm.tw.dao.UserRepository;
import com.utm.tw.entity.Registration;
import com.utm.tw.entity.User;
import com.utm.tw.entity.dto.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void activateUser(String url) {
        Registration registration = registrationRepository.findByRandomUrl(url);
        if (registration != null) {
            Optional<User> userFromDb = userRepository.findById(registration.getUserId());
            userFromDb.get().setRole("user_active");
            userRepository.save(userFromDb.get());
        }
    }

    public void register(RegisterDTO registerDTO) {
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        User toBeRegistred = new User(registerDTO);
        toBeRegistred.setRole("user");

        Registration registration = new Registration();
        registration.setUserId(userRepository.save(toBeRegistred).getId());
        registration.setRandomUrl(getSaltString());
        registrationRepository.save(registration);
        emailService.sendEmail(registerDTO.getEmail(), "Follow this link: http://localhost:8080/users/randomUrls/" + registration.getRandomUrl() , "Activate your account!");
    }

    private String getSaltString() {
        String saltChars = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * saltChars.length());
            salt.append(saltChars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

}

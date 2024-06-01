package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.RoleRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.repository.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setTotalEgeScore(userDto.getTotalEgeScore());
        user.setEgeScoredSubjects(userDto.getEgeScoredSubjects());
        user.setUniversitiesApplyingTo(userDto.getUniversitiesApplyingTo());

        // Check if the "ROLE_ADMIN" role exists, and create it if it doesn't
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = new Role();
            role.setName("ROLE_ADMIN");
            role = roleRepository.save(role);
        }

        // Assign the role to the user
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getName().split(" ")[0]);
        userDto.setLastName(user.getName().split(" ")[1]);
        userDto.setEmail(user.getEmail());
        userDto.setTotalEgeScore(user.getTotalEgeScore());
        userDto.setEgeScoredSubjects(user.getEgeScoredSubjects());
        userDto.setUniversitiesApplyingTo(user.getUniversitiesApplyingTo());
        return userDto;
    }
}

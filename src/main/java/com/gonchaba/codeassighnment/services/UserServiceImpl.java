package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.CalcUser;
import com.gonchaba.codeassighnment.dto.UserDto;
import com.gonchaba.codeassighnment.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CalcUser save(CalcUser user) {
        return userRepository.save(user);
    }

    @Override
    public CalcUser findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void delete(CalcUser user) {
        userRepository.delete(user);
    }

    @Override
    public List<CalcUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public CalcUser createUser(CalcUser user) {
        return userRepository.save(user);
    }

    @Override
    public CalcUser updateUser(String userName, UserDto user) {
        CalcUser existingUser = userRepository.findByUserName(userName);
        if (existingUser != null) {
            existingUser.setPassword(user.getPassword());
            existingUser.setUserName(user.getUserName());
            existingUser.setId(user.getId());
            existingUser.setBalance(existingUser.getBalance());
            existingUser.setOperations(existingUser.getOperations());
            existingUser.setStatus(existingUser.getStatus());
            existingUser.setName(existingUser.getName());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(String userName) {
        CalcUser user = userRepository.findByUserName(userName);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}


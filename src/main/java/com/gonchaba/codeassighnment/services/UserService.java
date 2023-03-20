package com.gonchaba.codeassighnment.services;

import com.gonchaba.codeassighnment.domain.CalcUser;

import java.util.List;

public interface UserService {
    CalcUser save(CalcUser user);

    CalcUser findByUserName(String userName);

    void delete(CalcUser user);

    List<CalcUser> getAllUsers();


    CalcUser createUser(CalcUser user);

    CalcUser updateUser(String userName, CalcUser user);

    void deleteUser(String userName);

    boolean userExists(String userName);

    boolean login(String userName, String password);
}

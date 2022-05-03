package com.qdu.PhotoSharing.service;

import com.qdu.PhotoSharing.entity.User;

public interface UserService {

    public User getUserById(int id);

    public User getUserByEmailAndPassword(String email, String password);

    public User getUserByFirstNameAndLastName(String firstName, String lastName);

}

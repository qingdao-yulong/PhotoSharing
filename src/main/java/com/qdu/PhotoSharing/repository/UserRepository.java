package com.qdu.PhotoSharing.repository;

import com.qdu.PhotoSharing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User getUserByEmailAndPassword(String email, String password);

    public User getUserByFirstNameAndLastName(String firstName, String lastName);

    public User getUserByEmail(String email);

}

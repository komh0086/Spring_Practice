package com.example.spring_practice.Domain.User.Repository;

import com.example.spring_practice.Domain.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

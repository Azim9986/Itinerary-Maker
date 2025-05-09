package com.example.itinerarymaker.repository;

import com.example.itinerarymaker.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM USER WHERE USERNAME = :username",
            nativeQuery = true)
    public User findByUsername(@Param("username") String username);

}

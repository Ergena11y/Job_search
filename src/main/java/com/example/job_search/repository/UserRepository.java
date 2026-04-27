package com.example.job_search.repository;

import com.example.job_search.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    
    Optional<User> findByPhoneNumber(String phoneNumber);

    Page<User> findByAccountType(String accountType, Pageable pageable);

    List<User> findByNameContainingIgnoreCaseOrSurnameContainingIgnoreCase(String name, String surname);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndAccountType(String email, String accountType);


    Optional<User> findByResetPasswordToken(String token);
}

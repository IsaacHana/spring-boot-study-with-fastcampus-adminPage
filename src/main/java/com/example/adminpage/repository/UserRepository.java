package com.example.adminpage.repository;

import com.example.adminpage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <인자 받아올 엔티티의 형식, pk (primaryKey)의 형식>
    Optional<User> findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}

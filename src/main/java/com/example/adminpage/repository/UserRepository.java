package com.example.adminpage.repository;

import com.example.adminpage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <인자 받아올 엔티티의 형식, pk (primaryKey)의 형식>
    // select * from user where account = ? << test03, test04
    Optional<User> findByAccount(String account);
    // select * from user where email = ? << TestUser03@gmail.com
    Optional<User> findByEmail(String email);

    // select * from user where account = ? and email = ?
    Optional<User> findByAccountAndEmail(String account, String email);
}

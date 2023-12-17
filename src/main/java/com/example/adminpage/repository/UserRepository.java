package com.example.adminpage.repository;

import com.example.adminpage.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // <인자 받아올 엔티티의 형식, pk (primaryKey)의 형식>
    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);
}

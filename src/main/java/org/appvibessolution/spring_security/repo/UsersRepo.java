package org.appvibessolution.spring_security.repo;

import org.appvibessolution.spring_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepo extends JpaRepository<Users, Integer> {

//    @Query(value = "SELECT * FROM users WHERE user_name = ?1", nativeQuery = true)
    Users findByUserName(String userName);
}

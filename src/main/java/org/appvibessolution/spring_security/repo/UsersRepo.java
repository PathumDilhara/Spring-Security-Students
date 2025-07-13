package org.appvibessolution.spring_security.repo;

import org.appvibessolution.spring_security.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findByUserName(String username);
}

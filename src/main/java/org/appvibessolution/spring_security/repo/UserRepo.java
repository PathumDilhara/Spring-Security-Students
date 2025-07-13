package org.appvibessolution.spring_security.repo;

import org.appvibessolution.spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUserName(String username);
}

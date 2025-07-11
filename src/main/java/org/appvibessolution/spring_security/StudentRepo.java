package org.appvibessolution.spring_security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    // Custom query methods can be defined here if needed
    // For example:
    // List<Student> findByLastName(String lastName);
}

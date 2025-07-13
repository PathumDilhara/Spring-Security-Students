package org.appvibessolution.spring_security.repo;

import org.appvibessolution.spring_security.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Integer> {

    // Custom query methods can be defined here if needed
    // For example:
    // List<Student> findByLastName(String lastName);
}

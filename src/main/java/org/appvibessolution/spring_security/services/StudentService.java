package org.appvibessolution.spring_security.services;

import jakarta.transaction.Transactional;
import org.appvibessolution.spring_security.dto.StudentDTO;
import org.appvibessolution.spring_security.model.Student;
import org.appvibessolution.spring_security.repo.StudentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepo.findAll();
        return modelMapper.map(students, new TypeToken<List<StudentDTO>>(){}.getType()) ;
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        studentRepo.save(modelMapper.map(studentDTO, Student.class));
        return studentDTO;
    }
}

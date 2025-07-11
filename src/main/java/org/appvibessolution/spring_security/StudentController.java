package org.appvibessolution.spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public List<StudentDTO> getStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/addStudent")
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO){
        return  studentService.addStudent(studentDTO);
    }

    // Method to get spring security csrf token
    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
}

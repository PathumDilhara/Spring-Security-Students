package org.appvibessolution.spring_security.controller;

import org.appvibessolution.spring_security.dto.UsersDTO;
import org.appvibessolution.spring_security.services.JWTService;
import org.appvibessolution.spring_security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private JWTService jwtService;

    @PostMapping("/create")
    public UsersDTO createUser(@RequestBody UsersDTO usersDTO) {
        // Logic to create a user
        return usersService.createUser(usersDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UsersDTO usersDTO) {
        return  usersService.verifyUser(usersDTO);
//        return "Login successful for user: " + usersDTO.getUserName();
    }

    @GetMapping("/token")
    public ResponseEntity<String> getToken(@RequestParam String username) {
        String token = jwtService.generateToken(username);
        return ResponseEntity.ok(token);
    }

}

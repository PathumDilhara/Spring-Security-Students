package org.appvibessolution.spring_security.controller;

import org.appvibessolution.spring_security.dto.UsersDTO;
import org.appvibessolution.spring_security.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/create")
    public UsersDTO createUser(@RequestBody UsersDTO usersDTO) {
        // Logic to create a user
        return usersService.createUser(usersDTO);
    }

}

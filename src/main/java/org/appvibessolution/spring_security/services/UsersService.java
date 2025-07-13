package org.appvibessolution.spring_security.services;

import jakarta.transaction.Transactional;
import org.appvibessolution.spring_security.dto.UsersDTO;
import org.appvibessolution.spring_security.model.Users;
import org.appvibessolution.spring_security.repo.UsersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JWTService jwtService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    @Autowired
    private AuthenticationManager authenticationManager;


    public UsersDTO createUser(UsersDTO usersDTO) {
        Users users = modelMapper.map(usersDTO, Users.class);

        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

        usersRepo.save(users);

        return modelMapper.map(usersRepo.findByUserName(users.getUserName()), UsersDTO.class);
    }

    public String verifyUser(UsersDTO usersDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                usersDTO.getUserName(), usersDTO.getPassword()));

        if(authentication.isAuthenticated()) {
//            return "Success";
            return jwtService.generateToken(usersDTO.getUserName());
        }

        return "Fail";
    }
}

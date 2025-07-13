package org.appvibessolution.spring_security.services;

import jakarta.transaction.Transactional;
import org.appvibessolution.spring_security.dto.UsersDTO;
import org.appvibessolution.spring_security.model.Users;
import org.appvibessolution.spring_security.repo.UsersRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsersService {

    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private ModelMapper modelMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);


    public UsersDTO createUser(UsersDTO usersDTO) {
        Users users = modelMapper.map(usersDTO, Users.class);

        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));

        usersRepo.save(users);

        return modelMapper.map(usersRepo.findByUserName(users.getUserName()), UsersDTO.class);
    }
}

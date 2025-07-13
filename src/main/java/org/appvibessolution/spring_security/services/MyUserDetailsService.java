package org.appvibessolution.spring_security.services;

import org.appvibessolution.spring_security.model.Users;
import org.appvibessolution.spring_security.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Users user = userRepo.findByUserName(userName);

        if(user == null){
            System.out.println("User not found with userName: " + userName);
            throw new UsernameNotFoundException("User not found with userName: " + userName);
        }

        return new UsersPrincipal(user);
    }
}

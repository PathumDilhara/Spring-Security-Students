package org.appvibessolution.spring_security.services;

import org.appvibessolution.spring_security.model.User;
import org.appvibessolution.spring_security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = userRepo.findByUserName(userName);

        if(user == null){
            System.out.println("User not found with username: " + userName);
            throw new UsernameNotFoundException("User not found with username: " + userName);
        }

        return new UserPrincipal(user);
    }
}

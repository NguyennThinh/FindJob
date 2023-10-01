package com.edu.job.security;

import com.edu.job.model.User;
import com.edu.job.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.getUserByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Không tìm thấy user");
        }

        return new CustomUserDetail(user);
    }
}

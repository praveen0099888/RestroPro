package com.inn.restaurant.jwt;

import com.inn.restaurant.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;


    private  com.inn.restaurant.POJO.User Userdetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("inside loduserbyusername {}", username);
        Userdetail = userDao.findByEmailId(username);
        log.info("inside loduserbyusername {}", Userdetail);
        if(!Objects.isNull(Userdetail))

            return new User(Userdetail.getEmail(),Userdetail.getPassword(),new ArrayList<>());

        else
            throw new UsernameNotFoundException("user not found.");
    }


    public  com.inn.restaurant.POJO.User getUserdetail(){
        return Userdetail;
    }
}

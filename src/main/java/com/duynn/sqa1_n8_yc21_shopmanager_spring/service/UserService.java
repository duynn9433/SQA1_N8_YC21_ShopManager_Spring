package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.User;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username){
        User user = userRepository.findUserByUsernameAndIsActiveIsTrue(username);
        Logger.getLogger(UserService.class.getName()).info("Find user: " + user);
        return user;
    }
}

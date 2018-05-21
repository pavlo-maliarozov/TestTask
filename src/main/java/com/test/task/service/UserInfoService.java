package com.test.task.service;

import com.test.task.model.UserInfo;
import com.test.task.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    public List<UserInfo> findAll() {

        return userInfoRepository.findAll();
    }

    public UserInfo getUserInfoById(long userId) {

        return userInfoRepository.getOne(userId);
    }

    public void saveUserInfo(Long userId,
                             String firstName,
                             String lastName,
                             Date birthday,
                             char gender) {

        UserInfo userInfo;

        if (userId != null) {
            userInfo = userInfoRepository.getOne(userId);
        } else {
            userInfo = new UserInfo();
        }

        userInfo.setFirstName(firstName);
        userInfo.setLastName(lastName);
        userInfo.setBirthday(birthday);
        userInfo.setGender(gender);

        userInfoRepository.save(userInfo);

    }

    public void deleteUserInfoById(long userId) {

        userInfoRepository.deleteById(userId);
    }
}

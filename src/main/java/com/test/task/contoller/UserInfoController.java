package com.test.task.contoller;

import com.test.task.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @RequestMapping("/")
    public ModelAndView homePage() {

        ModelAndView modelAndView = new ModelAndView("index");

        modelAndView.addObject("userList", userInfoService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView saveUser(@RequestParam("userId") Long userId,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("birthday") Date birthday,
                                 @RequestParam("gender") char gender) {

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        userInfoService.saveUserInfo(userId, firstName, lastName, birthday, gender);

        return modelAndView;
    }

    @RequestMapping(value = "/view/{user_id}", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable("user_id") long userId) {

        ModelAndView modelAndView = new ModelAndView("view");

        modelAndView.addObject("user",
                userInfoService.getUserInfoById(userId));

        return modelAndView;
    }

    @RequestMapping(value = "/delete/{user_id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("user_id") long userId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        userInfoService.deleteUserInfoById(userId);

        return modelAndView;
    }

    @RequestMapping(value = "/edit/{user_id}", method = RequestMethod.GET)
    public ModelAndView editUser(@PathVariable("user_id") long userId) {

        ModelAndView modelAndView = new ModelAndView("edit");

        modelAndView.addObject("user",
                userInfoService.getUserInfoById(userId));

        return modelAndView;
    }
}

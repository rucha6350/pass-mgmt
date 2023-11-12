package com.rucha.passmgmtservice.controller;

import com.rucha.passmgmtservice.service.UserService;
import com.rucha.passmgmtservice.vo.ResponseVO;
import com.rucha.passmgmtservice.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/getUserByEmail")
    public UserVO getUserByEmailId(@RequestParam("email") String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping("/registerUser")
    public ResponseVO registerUser(@RequestBody UserVO userVO) {
        return userService.registerUser(userVO);
    }

}

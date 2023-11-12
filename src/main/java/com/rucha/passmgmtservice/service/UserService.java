package com.rucha.passmgmtservice.service;

import com.rucha.passmgmtservice.vo.ResponseVO;
import com.rucha.passmgmtservice.vo.UserVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserVO getUserByEmail(String email);

    ResponseVO registerUser(UserVO userVO);
}

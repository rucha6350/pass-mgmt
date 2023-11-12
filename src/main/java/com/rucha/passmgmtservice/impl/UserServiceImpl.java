package com.rucha.passmgmtservice.impl;

import com.rucha.passmgmtservice.entity.UserEntity;
import com.rucha.passmgmtservice.repo.UserRepo;
import com.rucha.passmgmtservice.service.UserService;
import com.rucha.passmgmtservice.vo.ResponseVO;
import com.rucha.passmgmtservice.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserVO getUserByEmail(String email){
        UserVO userVO = new UserVO();
        Optional<UserEntity> userEntityOptional = userRepo.findByEmail(email);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            userVO.setUserName(userEntity.getUserName());
            userVO.setEmail(userEntity.getEmail());
            userVO.setMobile(userEntity.getMobile());
            return userVO;
        }
        return userVO;

    }

    @Override
    public ResponseVO registerUser(UserVO userVO) {
        UserEntity userEntity = new UserEntity();
        ResponseVO responseVO = new ResponseVO();
        userEntity.setUserName(userVO.getUserName());
        userEntity.setEmail(userVO.getEmail());
        userEntity.setMobile(userVO.getMobile());
        userEntity.setPassword(userVO.getPassword());
        userRepo.save(userEntity);
        responseVO.setStatusCode(200);
        responseVO.setStatus("USER ADDED SUCCESSFULLY");
        return responseVO;
    }
}

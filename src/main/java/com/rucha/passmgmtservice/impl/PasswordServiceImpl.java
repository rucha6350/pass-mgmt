package com.rucha.passmgmtservice.impl;

import com.rucha.passmgmtservice.entity.PasswordEntity;
import com.rucha.passmgmtservice.repo.PasswordRepo;
import com.rucha.passmgmtservice.service.PasswordService;
import com.rucha.passmgmtservice.util.PasswordEDUtil;
import com.rucha.passmgmtservice.vo.PasswordVO;
import com.rucha.passmgmtservice.vo.ResponseVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PasswordServiceImpl implements PasswordService {

    private final PasswordRepo passwordRepo;

    public PasswordServiceImpl(PasswordRepo passwordRepo){
        this.passwordRepo = passwordRepo;
    }
    @Override
    public List<PasswordVO> getMyPasswordList(Integer userId){
        List<PasswordEntity> passwordEntities = passwordRepo.findAllByUserId(userId);
        List<PasswordVO> passwordVOList = new ArrayList<>();
        for(PasswordEntity passwordEntity : passwordEntities){
            PasswordVO passwordVO = new PasswordVO();
            passwordVO.setPasswordName(passwordEntity.getPasswordName());
            passwordVO.setPasswordStored(PasswordEDUtil.decrypt(passwordEntity.getPasswordStored()));
            passwordVO.setUserId(passwordEntity.getUserId());
            passwordVO.setCategoryId(passwordEntity.getCategoryId()
            );
            passwordVOList.add(passwordVO);
        }
        return passwordVOList;
    }

    @Override
    public ResponseVO addPassword(PasswordVO passwordVO){
        ResponseVO responseVO = new ResponseVO();
        PasswordEntity passwordEntity = new PasswordEntity();
        passwordEntity.setPasswordName(passwordVO.getPasswordName());
        passwordEntity.setPasswordStored(PasswordEDUtil.encrypt(passwordVO.getPasswordStored()));
        passwordEntity.setUserId(passwordVO.getUserId());
        passwordEntity.setCategoryId(passwordVO.getCategoryId());
        passwordRepo.save(passwordEntity);
        responseVO.setStatusCode(200);
        responseVO.setStatus("PASSWORD ADDED SUCCESSFULLY");
        return responseVO;
    }

    @Override
    public PasswordVO getPasswordById(Integer passwordId) {
        Optional<PasswordEntity> passwordEntity = passwordRepo.findById(passwordId);
        if (passwordEntity.isPresent()){
            PasswordEntity passwordData = passwordEntity.get();
            PasswordVO passwordVO = new PasswordVO();
            passwordVO.setPasswordName(passwordData.getPasswordName());
            passwordVO.setPasswordStored(PasswordEDUtil.decrypt(passwordData.getPasswordStored()));
            passwordVO.setUserId(passwordData.getUserId());
            passwordVO.setCategoryId(passwordData.getCategoryId());
            return passwordVO;
        }else {
            return null;
        }
    }

    @Override
    public PasswordVO updatePassword(PasswordEntity passwordEntity) {
        Optional<PasswordEntity> passwordEntityDb = passwordRepo.findById(passwordEntity.getId());
        if (passwordEntityDb.isPresent()){
            PasswordEntity passwordData = passwordEntityDb.get();
            PasswordEntity updatedPassword = new PasswordEntity();
            updatedPassword.setId(passwordData.getId());
            updatedPassword.setPasswordName(passwordEntity.getPasswordName());
            updatedPassword.setPasswordStored(PasswordEDUtil.encrypt(passwordEntity.getPasswordStored()));
            updatedPassword.setUserId(passwordData.getUserId());
            updatedPassword.setCategoryId(passwordEntity.getCategoryId());
            passwordRepo.save(updatedPassword);

            PasswordVO passwordVO = new PasswordVO();
            passwordVO.setPasswordName(updatedPassword.getPasswordName());
            passwordVO.setPasswordStored(PasswordEDUtil.decrypt(updatedPassword.getPasswordStored()));
            passwordVO.setUserId(updatedPassword.getUserId());
            passwordVO.setCategoryId(updatedPassword.getCategoryId());
            return passwordVO;
        }else {
            return null;
        }
    }

    @Override
    public ResponseVO deletePassword(Integer passwordId) {
        passwordRepo.deleteById(passwordId);
        ResponseVO responseVO = new ResponseVO();
        responseVO.setStatusCode(200);
        responseVO.setStatus("DELETED SUCCESSFULLY");
        return responseVO;
    }

}

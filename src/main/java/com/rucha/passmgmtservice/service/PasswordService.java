package com.rucha.passmgmtservice.service;

import com.rucha.passmgmtservice.entity.PasswordEntity;
import com.rucha.passmgmtservice.vo.PasswordVO;
import com.rucha.passmgmtservice.vo.ResponseVO;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public interface PasswordService {
    List<PasswordVO> getMyPasswordList(Integer userId);
    ResponseVO addPassword(PasswordVO passwordVO);
    PasswordVO getPasswordById(Integer passwordId);
    PasswordVO updatePassword(PasswordEntity passwordEntity);
    ResponseVO deletePassword(Integer passwordId);
}

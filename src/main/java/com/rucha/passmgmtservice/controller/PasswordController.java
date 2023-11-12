package com.rucha.passmgmtservice.controller;

import com.rucha.passmgmtservice.entity.PasswordEntity;
import com.rucha.passmgmtservice.service.PasswordService;
import com.rucha.passmgmtservice.vo.PasswordVO;
import com.rucha.passmgmtservice.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/getMyPasswords")
    public List<PasswordVO> getMyPasswordList(@RequestParam Integer userId) {
        return passwordService.getMyPasswordList(userId);
    }

    @GetMapping("/getPassword")
    public PasswordVO getPasswordById(@PathVariable Integer passwordId){
        return passwordService.getPasswordById(passwordId);
    }

    @PostMapping("/addPassword")
    public ResponseVO addPassword(@RequestBody PasswordVO passwordVO){
        return passwordService.addPassword(passwordVO);
    }

    @PutMapping("/updatePassword")
    public PasswordVO updatePassword(@RequestBody PasswordEntity passwordEntity){
        return passwordService.updatePassword(passwordEntity);
    }

    @DeleteMapping("/deletePassword/{id}")
    public ResponseVO deletePassword(@PathVariable("id") Integer passwordId){
        return passwordService.deletePassword(passwordId);
    }
}

package com.rucha.passmgmtservice.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {
    private String userName;
    private String email;
    private String mobile;
    private String password;
}

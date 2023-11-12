package com.rucha.passmgmtservice.vo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordVO {
    private String passwordName;
    private String passwordStored;
    private Integer userId;
    private Integer categoryId;
}

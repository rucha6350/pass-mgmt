package com.rucha.passmgmtservice.repo;

import com.rucha.passmgmtservice.entity.PasswordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordRepo extends JpaRepository<PasswordEntity, Integer> {

    List<PasswordEntity> findAllByUserId(Integer userId);
}

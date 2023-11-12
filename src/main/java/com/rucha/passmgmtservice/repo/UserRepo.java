package com.rucha.passmgmtservice.repo;

import com.rucha.passmgmtservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);
}


package com.ykbt.orm.repository;

import org.springframework.stereotype.Repository;
import com.ykbt.orm.entity.UserEntity;

@Repository
public interface JpaRepository extends org.springframework.data.jpa.repository.JpaRepository<UserEntity, Integer>{
}

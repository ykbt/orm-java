package com.ykbt.orm.dao;

import com.ykbt.orm.entity.UserDomaEntity;
import com.ykbt.orm.entity.UserEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;

import java.util.List;

@ConfigAutowireable
@Dao
public interface DomaDao {

    @Select
    List<UserDomaEntity> findAll();

    @Select
    UserDomaEntity get(int id);

    @Insert
    int save(UserDomaEntity entity);

    @Update
    int update(UserDomaEntity entity);

    @Delete(sqlFile = true)
    int delete(int id);
}

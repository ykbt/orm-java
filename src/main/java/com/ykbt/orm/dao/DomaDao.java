package com.ykbt.orm.dao;

import com.ykbt.orm.entity.UserEntity;
import org.seasar.doma.*;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.SqlLogType;

import java.util.List;

@ConfigAutowireable
@Dao
public interface DomaDao {

    @Select(sqlLog = SqlLogType.RAW)
    List<UserEntity> findAll();

    @Select(sqlLog = SqlLogType.RAW)
    UserEntity get(int id);

    @Insert(sqlLog = SqlLogType.RAW)
    int save(UserEntity entity);

    @Update(sqlLog = SqlLogType.RAW)
    int update(UserEntity entity);

    @Delete(sqlFile = true)
    int delete(int id);
}

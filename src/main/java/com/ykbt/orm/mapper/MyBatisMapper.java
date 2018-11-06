package com.ykbt.orm.mapper;

import com.ykbt.orm.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyBatisMapper {
    @Select("SELECT * FROM user")
    List<UserEntity> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity get(Integer id);

    @Insert("INSERT INTO user (first_name, last_name) VALUES (#{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, flushCache = Options.FlushCachePolicy.TRUE )
    @SelectKey(statement = "select @@identity", keyProperty = "id", resultType = int.class, before = false)
    int save(UserEntity entity);

    @Update("UPDATE user SET first_name = #{firstName}, last_name = #{lastName} WHERE id = #{id}")
    void update(UserEntity entity);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void delete(Integer id);
}

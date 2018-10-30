package ykbt.orm.mapper;

import org.apache.ibatis.annotations.*;
import ykbt.orm.entity.UserEntity;

import java.util.List;

@Mapper
public interface MyBatisMapper {

    @Select("SELECT * FROM user")
    List<UserEntity> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserEntity get(Integer id);

    @Insert("INSERT INTO user (fisrt_name, last_name) VALUES (#{firstName}, #{lastName})")
    @SelectKey(statement = "select @@IDENTITY", keyProperty = "id", resultType = Integer.class, before = false)
    @Options(useGeneratedKeys = true)
    Integer save(UserEntity entity);

    @Update("UPDATE user SET first_name = #{firstName}, lastName = #{lastName} WHERE id = #{id}")
    @SelectKey(statement = "select @@IDENTITY", keyProperty = "id", resultType = Integer.class, before = false)
    Integer update(UserEntity entity);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void delete(Integer id);
}

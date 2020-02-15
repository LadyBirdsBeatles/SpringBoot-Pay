package com.xiaochen.beatles.mapper;


import com.xiaochen.beatles.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int countByExample(User user);

    int deleteByExample(User user);

    /**
     * @param id
     * @return
     */
    @Delete(value = "delete from user where id=#{id}")
    int deleteByPrimaryKey(String id);

    /**
     * 添加用户
     *
     * @param record
     * @return
     */
    @Insert(value = "INSERT INTO user(username,sex)" + "VALUE(#{username},#{sex})")
    int insert(User record);

    /**
     * 选择性添加用户属性
     *
     * @param record
     * @return
     */
    @InsertProvider(type = UserDynamicSqlProvider.class, method = "insertSelective")
    int insertSelective(User record);

    /**
     * @param example
     * @return
     */
    @Results(
            id = "id", value = {
            @Result(property = "username", column = "username"),
            @Result(property = "sex", column = "sex")
    })
    @Select(value = "select *from user ")
    List<User> selectByExample();

    /**
     * @param id
     * @return
     */
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "sex", column = "sex")
    })
    @Select(value = "select *from user where id=#{id}")
    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") User user);

    int updateByExample(@Param("record") User record, @Param("example") User user);

    /**
     * @param record
     * @return
     */
    @UpdateProvider(type = UserDynamicSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
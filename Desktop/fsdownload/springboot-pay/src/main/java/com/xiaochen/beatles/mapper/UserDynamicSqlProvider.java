package com.xiaochen.beatles.mapper;

import com.xiaochen.beatles.pojo.User;
import org.apache.ibatis.jdbc.SQL;

public class UserDynamicSqlProvider {
    /**
     * 添加用户的属性SQL
     *
     * @param user
     * @return
     */
    public String insertSelective(User user) {
        return new SQL() {
            {
                INSERT_INTO("user");
                if (user.getId() != null) {
                    VALUES("id", "#{id}");
                }
                if (user.getUsername() != null) {
                    VALUES("username", "#{username}");
                    //SET("name", person.getName());
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }
                if (user.getSex() != null) {
                    VALUES("sex", "#{sex}");
                }
            }
        }.toString();
    }

    public String updateByPrimaryKeySelective(User user) {
        return new SQL() {
            {
                UPDATE("user");
                if (user.getUsername() != null) {
                    SET("username=#{username}");
                    //SET("name", person.getName());
                    //Caused by: com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Unknown column 'Java' in 'field list'
                }
                if (user.getSex() != null) {
                    SET("sex=#{sex}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }
}

package com.eastflag.fullstack.persistence;

import com.eastflag.fullstack.domain.BoardVO;
import com.eastflag.fullstack.domain.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AuthMapper {

    @Select({"<script>",
            "SELECT * from user",
            "WHERE email = #{email}",
            "LIMIT 1",
            "</script>"})
    UserVO findOneByEmail(String email);

    @Select({"<script>",
            "SELECT count(*) from user",
            "WHERE email = #{email}",
            "</script>"})
    int existByEmail(String email);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({"<script>",
            "INSERT INTO user(email, password, username)",
            "VALUES(#{email}, #{password}, #{username})",
            "</script>"})
    int insertUser(UserVO user);

    @Insert({"<script>",
            "INSERT INTO user_role(user_id, role_id)",
            "VALUES(#{user_id}, (SELECT id FROM role where name = #{roleName}))",
            "</script>"})
    int insertUserRole(Long user_id, String roleName);
}

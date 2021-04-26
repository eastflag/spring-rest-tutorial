package com.eastflag.fullstack.persistence;

import com.eastflag.fullstack.domain.CommentVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert({"<script>",
            "INSERT INTO comment(content, board_id)",
            "VALUES(#{content}, #{board_id})",
            "</script>"})
    int insertComment(CommentVO commentVO);

    @Select({"<script>",
            "SELECT * from comment",
            "order by id desc",
            "</script>"})
    List<CommentVO> findComment(int board_id);

    @Update({"<script>",
            "UPDATE comment",
            "set content = #{content}",
            "WHERE id = #{id}",
            "</script>"})
    int updateComment(CommentVO commentVO);

    @Delete({"<script>",
            "DELETE FROM board",
            "WHERE id = #{id}",
            "</script>"})
    int deleteComment(int id);
}

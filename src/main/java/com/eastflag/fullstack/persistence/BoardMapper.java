package com.eastflag.fullstack.persistence;

import com.eastflag.fullstack.domain.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Insert({"<script>",
            "INSERT INTO board(title, content)",
            "VALUES(#{title}, #{content})",
            "</script>"})
    int insertBoard(BoardVO boardVO);

    @Select({"<script>",
            "SELECT * from board",
            "order by id desc",
            "<if test='offset != null and page_size != null'>",
            "LIMIT #{offset}, #{page_size}",
            "</if>",
            "</script>"})
    List<BoardVO> findBoard(Integer offset, Integer page_size);

    @Select({"<script>",
            "SELECT * from board",
            "where id = #{id}",
            "</script>"})
    BoardVO findOneBoard(int id);

    @Update({"<script>",
            "UPDATE board",
            "<trim prefix='set' suffixOverrides=','>",
            "<if test='title != null'>title = #{title},</if>",
            "<if test='content != null'>content = #{content},</if>",
            "</trim>",
            "WHERE id = #{id}",
            "</script>"})
    int updateBoard(BoardVO boardVO);

    @Delete({"<script>",
            "DELETE FROM board",
            "WHERE id = #{id}",
            "</script>"})
    int deleteBoard(int id);
}

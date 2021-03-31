package com.eastflag.fullstack.persistence;

import com.eastflag.fullstack.domain.ImageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ImageMapper {

    @Insert({"<script>",
            "INSERT INTO image(mimetype, original_name, data)",
            "VALUES(#{mimetype}, #{original_name}, #{data})",
            "</script>"})
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertBoard(ImageVO imageVO);

    @Select({"<script>",
            "SELECT * from image",
            "where id = #{id}",
            "</script>"})
    ImageVO findOneImage(int id);
}

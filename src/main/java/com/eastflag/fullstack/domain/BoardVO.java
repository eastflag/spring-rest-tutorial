package com.eastflag.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BoardVO {
    private Integer id;
    private String title;
    private String content;
    private String created;
    private String updated;
}

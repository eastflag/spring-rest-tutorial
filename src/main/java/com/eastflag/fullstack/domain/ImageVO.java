package com.eastflag.fullstack.domain;

import lombok.Data;

@Data
public class ImageVO {
    private Integer id;
    private String mimetype;
    private String original_name;
    private byte[] data;
    private String created;
}

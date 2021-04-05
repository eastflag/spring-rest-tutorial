package com.eastflag.fullstack.controller;

import com.eastflag.fullstack.domain.ImageVO;
import com.eastflag.fullstack.persistence.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/image")
public class ImageController {
    private final ImageMapper imageMapper;

    @PostMapping("/upload")
    public Integer handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        ImageVO imageVO = new ImageVO();
        imageVO.setMimetype(file.getContentType());
        imageVO.setOriginal_name(file.getOriginalFilename());
        imageVO.setData(file.getBytes());
        imageMapper.insertBoard(imageVO);

        return imageVO.getId();
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> findOne(@PathVariable int id) {
        ImageVO imageVO = imageMapper.findOneImage(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", imageVO.getMimetype());
        headers.add("Content-Length", String.valueOf(imageVO.getData().length));

        return new ResponseEntity<byte[]>(imageVO.getData(), headers, HttpStatus.OK);
    }
}

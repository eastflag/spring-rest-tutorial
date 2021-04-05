package com.eastflag.fullstack.controller;

import com.eastflag.fullstack.domain.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello test";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello GET test";
    }

    @GetMapping("/hello3")
    public String hello3(@RequestParam("name") String name) {
        return "Hello" + name;
    }

    @GetMapping("/hello32/{name}")
    public String hello32(@PathVariable String name) {
        return "Hello" + name;
    }

    @PostMapping("/hello33")
    public String hello33(@RequestParam("name") String name) {
        return "Hello" + name;
    }

    @PostMapping("/hello4")
    public String hello4(@RequestParam String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello5")
    public ResultVO hello5(@RequestParam("name") String name) {
        ResultVO result = new ResultVO(0, name);
        return result;
    }

    @PostMapping("/hello6")
    public ResultVO hello6(@RequestBody ResultVO result) {
        return result;
    }
}

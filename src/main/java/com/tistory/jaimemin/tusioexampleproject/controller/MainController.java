package com.tistory.jaimemin.tusioexampleproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String test() {
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam MultipartFile file
            , HttpServletRequest request) throws IOException {
        log.info("request = {}", request);
        log.info("multipartFile = {}", file);

        if (!file.isEmpty()) {
            File dir = new File("dest/" + file.getOriginalFilename());

            if (!dir.exists()) {
                dir.mkdir();
            }
        }

        return "upload-form";
    }
}

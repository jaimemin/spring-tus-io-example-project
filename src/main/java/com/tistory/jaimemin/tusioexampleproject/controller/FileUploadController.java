package com.tistory.jaimemin.tusioexampleproject.controller;

import com.tistory.jaimemin.tusioexampleproject.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 참고: https://qiita.com/Amtkxa/items/6a605209bcc6eb477ec0
 * 참고: https://github.com/tus/tus-js-client/blob/master/docs/usage.md
 * 참고: https://stackoverflow.com/questions/72917279/how-to-upload-multiple-files-using-tus-protocol
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @RequestMapping(value = {"/api/tus/file/upload", "/api/tus/file/upload/**"})
    public ResponseEntity uploadWithTus(HttpServletRequest request, HttpServletResponse response) {
        // Process a tus upload request
        fileUploadService.process(request, response);

        // Generate HTTP Response Headers
        return httpOkStatus();
    }

    private static ResponseEntity<Object> httpOkStatus() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");

        return ResponseEntity.status(HttpStatus.OK)
                .headers(headers)
                .build();
    }
}

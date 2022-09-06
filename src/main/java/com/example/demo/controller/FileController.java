package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import com.example.demo.service.FileService;
import com.example.demo.util.PassToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/file")
@Validated
public class FileController {
    @Autowired
    FileService fileService;

    @PassToken
    @PostMapping("/upload")
    public ApiResponse fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BlogException(BlogExceptionEnum.FILE_UPLOAD_ERROR);
        }
        File file = fileService.fileUpload(multipartFile);
        return ApiResponse.success(file);
    }
}

package com.example.demo.service;

import com.example.demo.common.Constant;
import com.example.demo.exception.BlogException;
import com.example.demo.exception.BlogExceptionEnum;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {
    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    public File fileUpload(MultipartFile multipartFile) {
        String filePath = Constant.FILE_PATH;
        String fileName = String.valueOf(System.currentTimeMillis()) + "_" + multipartFile.getOriginalFilename();
        File file = new File(filePath + File.separator + fileName);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
            log.debug("=======file upload success=======");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件关闭错误", e);
                throw new BlogException(BlogExceptionEnum.FILE_UPLOAD_ERROR);
            }
        }

        return file;
    }
}

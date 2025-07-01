package com.example.crud_demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/files")
public class FileUploadController {
    /**上传单个文件
     * 请求类型要为post，请求头要将Content-Type改为"multipart/form-data",
     * 请求body中key name与@RequestParam一致“file”，value type要改为File，value从本地选择一个文件
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uniqueFileName = UUID.randomUUID() + fileExtension;

        String uploadDir = "/users/zss0527/desktop/";

        Path targetPath = Paths.get(uploadDir, uniqueFileName);

        new File(uploadDir).mkdirs();

        Files.copy(file.getInputStream(),targetPath);

        return ResponseEntity.ok("upload success!");
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<List<String>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        if(files == null || files.length == 0) {
            return ResponseEntity.badRequest().body(List.of("error: didn't find files"));
        }
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file: files) {
            try {
                String response = uploadFile(file).getBody();
                fileNames.add(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(fileNames);
    }
}

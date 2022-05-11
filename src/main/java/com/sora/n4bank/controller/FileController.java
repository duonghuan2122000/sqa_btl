package com.sora.n4bank.controller;

import com.sora.n4bank.configuration.StorageProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RequestMapping("/files")
@Controller
public class FileController {

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final Path root;

    @Autowired
    public FileController(StorageProperties storageProperties) {
        root = Paths.get(storageProperties.getLocation());
    }

    @GetMapping("")
    public ResponseEntity<Resource> streamFile(@RequestParam(name = "fileName") String fileName) {
        try {
            Path file = root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
        } catch(MalformedURLException e){

        }
        return null;
    }

    @ResponseBody
    @PostMapping("")
    public List<String> uploadFile(@RequestParam(name = "file") MultipartFile[] files, HttpServletRequest request) {
        List<String> fileNames = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                String fileExt = getExtensionFile(file.getOriginalFilename());
                String fileName = UUID.randomUUID() + fileExt;
                fileNames.add(fileName);
                Files.copy(file.getInputStream(), root.resolve(fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    private String getExtensionFile(String fileName) {
        return fileName.toLowerCase().substring(fileName.lastIndexOf("."));
    }
}

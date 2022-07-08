package com.example.index_market.entity.product;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class ImageService {

    @Value(value = "${image.folder.path}")
    private String uploadedFiles;


    public String saveToFileSystem(MultipartFile file) throws Exception {
        String imageUrl ;

        String[] split = file.getOriginalFilename().split("\\.");
        String name = UUID.randomUUID() + "." + split[split.length - 1];
        Path path = Paths.get(uploadedFiles + name);
        Files.copy(file.getInputStream(), path);
        imageUrl = path.toString();

        return imageUrl;
    }

    public String getFileFromRequest(MultipartHttpServletRequest request) throws Exception {
        Iterator<String> fileNames = request.getFileNames();
        String imageId = null;
        while (fileNames.hasNext()) {
            String filename = fileNames.next();
              MultipartFile file = request.getFile(filename);

                if(file != null) {
                    imageId = saveToFileSystem(file);
                }

            }

        return imageId;
    }
}

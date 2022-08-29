package com.example.index_market.bot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @Value(value = "${image.folder.path}")
    private String uploadedFiles;

    @GetMapping
    public void getFileByName(@RequestParam String name, HttpServletResponse response) throws IOException {
        File file = new File(uploadedFiles + name);
        response.setContentType("image/png");
        response.setHeader("Cache-Control","max-age=8640000");
        response.setHeader("Content-disposition","attachment; filename=\"" + file.getName() +"\"");
        InputStream inputStream = new FileInputStream(file);
         FileCopyUtils.copy(inputStream,response.getOutputStream());
    }
}

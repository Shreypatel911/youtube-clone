package com.example.youtubeclone.controller;

import com.example.youtubeclone.model.Video;
import com.example.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Video uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        return videoService.uploadVideo(file);
    }
}
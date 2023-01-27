package com.baixaisso.baixaissoowebsite.controllers;

import com.baixaisso.baixaissoowebsite.model.User;
import com.baixaisso.baixaissoowebsite.model.Video;
import com.baixaisso.baixaissoowebsite.services.UserService;
import com.baixaisso.baixaissoowebsite.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class UserController {

    private final UserService userService;

    private final VideoService videoService;

    @Autowired
    public UserController(UserService userService,VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
    }

    @GetMapping("/user")
    public String loadVideosPage(@RequestParam String userScreenName, Model model){
        User user =  userService.getUser(userScreenName);
        Page<Video> pageVideos = videoService.getVideos(user.getTwitterUserId(),0,2);
        model.addAttribute("videos",pageVideos);

        return "showVideos";
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String path) throws MalformedURLException {
        URL fileUrl = new URL(path);
        Resource resource = new UrlResource(fileUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=video.mp4");

        return ResponseEntity.ok().headers(headers).contentLength(-1).body(resource);
    }

}

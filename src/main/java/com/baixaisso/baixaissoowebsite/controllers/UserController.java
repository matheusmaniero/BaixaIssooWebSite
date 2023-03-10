package com.baixaisso.baixaissoowebsite.controllers;

import com.baixaisso.baixaissoowebsite.model.User;
import com.baixaisso.baixaissoowebsite.model.Video;
import com.baixaisso.baixaissoowebsite.services.UserService;
import com.baixaisso.baixaissoowebsite.services.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.concurrent.ExecutionException;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    private final VideoService videoService;

    @Autowired
    public UserController(UserService userService,VideoService videoService) {
        this.userService = userService;
        this.videoService = videoService;
    }

    @GetMapping("/u")
    public String loadVideosPage(@RequestParam(name = "user") String userScreenName, @RequestParam (defaultValue = "1") int page, Model model) throws Exception {
        userScreenName = userScreenName.replaceAll("@","").trim().toLowerCase();
        User user =  userService.getUser(userScreenName);
        if (page < 1){
           page =1;
        }
        if (user != null){
            Page<Video> pageVideos = videoService.getVideos(user.getTwitterUserId(),page-1,9);
            int totalPages = pageVideos.getTotalPages();
            model.addAttribute("videos",pageVideos);
            model.addAttribute("currentPage",page);
            model.addAttribute("totalPages",totalPages);
            model.addAttribute("user",userScreenName);

    }
        return "videosByUser";
   }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String path) throws MalformedURLException {
        URL fileUrl = new URL(path);
        Resource resource = new UrlResource(fileUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "video/mp4");
        headers.add("Content-Disposition", "attachment; filename=video.mp4");

        return ResponseEntity.ok().headers(headers).contentLength(-1).body(resource);
    }

}

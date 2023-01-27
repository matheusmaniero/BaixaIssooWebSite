package com.baixaisso.baixaissoowebsite.controllersTest;

import com.baixaisso.baixaissoowebsite.model.User;
import com.baixaisso.baixaissoowebsite.model.Video;
import com.baixaisso.baixaissoowebsite.services.VideoService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VideoService videoService;

    @Before
    public void setup(){

        User user = new User(1403858370730278915L,"maniero87929783");
        Video video1 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1616381396838191104/pu/vid/636x360/tVhGyKRk7_xMkrk8.mp4?tag=12",1674213485000L); //Fri Jan 20 2023 11:18:05
        Video video2 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615866775710896129/pu/vid/404x720/DbhLgjv2kIusdoP8.mp4?tag=12",1674212857000L); // Fri Jan 20 2023 11:07:37
        Video video3 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615303762142457857/pu/vid/360x638/cmaWfKEyS5d058DQ.mp4?tag=12",1674133973000L); // Thu Jan 19 2023 13:12:53
        List<Video> videos = Arrays.asList(video1,video2,video3);
        Pageable pageable = PageRequest.of(0,2, Sort.by("createdAt").descending());
        Page<Video> page = new PageImpl<>(videos,pageable,videos.size());
        Mockito.when(videoService.getVideos(user.getTwitterUserId(), 0,2)).thenReturn(page);


    }

    @Test
    public void shouldReturnViewWithPageable() throws Exception {
        User user = new User(1403858370730278915L,"maniero87929783");
        Page<Video> pageVideos = videoService.getVideos(user.getTwitterUserId(), 0,2);

        mockMvc.perform(get("/user?userScreenName=maniero87929783"))
                .andExpect(status().isOk())
                .andExpect(view().name("showVideos"))
                .andExpect(model().attribute("videos",pageVideos));

    }


    @Test
    public void shouldReturnCorrectHeader() throws Exception {
        String path = "https://video.twimg.com/ext_tw_video/1616381396838191104/pu/vid/636x360/tVhGyKRk7_xMkrk8.mp4?tag=12";

                mockMvc.perform(get("/download")
                        .param("path", path)
                        .header("Content-Disposition", "attachment; filename=video.mp4"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=video.mp4"));

    }


    }





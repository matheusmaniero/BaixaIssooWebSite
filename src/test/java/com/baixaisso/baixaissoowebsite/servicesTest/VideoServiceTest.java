package com.baixaisso.baixaissoowebsite.servicesTest;

import com.baixaisso.baixaissoowebsite.model.User;
import com.baixaisso.baixaissoowebsite.model.Video;
import com.baixaisso.baixaissoowebsite.repositories.VideoRepository;
import com.baixaisso.baixaissoowebsite.services.VideoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class VideoServiceTest {

    @Autowired
    VideoService videoService;
    @MockBean
    VideoRepository videoRepository;

    @TestConfiguration
    static class videoServiceTestConfiguration {
        @Bean
        public VideoService videoService(){
            return new VideoService();
        }
    }

    @Before
    public void setup(){
        User user = new User(1403858370730278915L,"maniero87929783");
        Video video1 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1616381396838191104/pu/vid/636x360/tVhGyKRk7_xMkrk8.mp4?tag=12",1674213485000L); //Fri Jan 20 2023 11:18:05
        Video video2 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615866775710896129/pu/vid/404x720/DbhLgjv2kIusdoP8.mp4?tag=12",1674212857000L); // Fri Jan 20 2023 11:07:37
        Video video3 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615303762142457857/pu/vid/360x638/cmaWfKEyS5d058DQ.mp4?tag=12",1674133973000L); // Thu Jan 19 2023 13:12:53
        List<Video> videos = Arrays.asList(video1,video2,video3);
        Pageable pageable = PageRequest.of(0,2, Sort.by("createdAt").descending());
        Page<Video> page = new PageImpl<>(videos,pageable,videos.size());

        Mockito.when(videoRepository.findByOwnerId(user.getTwitterUserId(),pageable)).thenReturn(page);
    }
    @Test
    public void shouldReturnAPageOfVideos(){

        User user = new User(1403858370730278915L,"maniero87929783");
        Video video1 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1616381396838191104/pu/vid/636x360/tVhGyKRk7_xMkrk8.mp4?tag=12",1674213485000L); //Fri Jan 20 2023 11:18:05
        Video video2 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615866775710896129/pu/vid/404x720/DbhLgjv2kIusdoP8.mp4?tag=12",1674212857000L); // Fri Jan 20 2023 11:07:37
        Video video3 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615303762142457857/pu/vid/360x638/cmaWfKEyS5d058DQ.mp4?tag=12",1674133973000L); // Thu Jan 19 2023 13:12:53
        List<Video> videos = Arrays.asList(video1,video2,video3);
        Pageable pageable = PageRequest.of(0,2, Sort.by("createdAt").descending());
        Page<Video> page = new PageImpl<>(videos,pageable,videos.size());

        Page<Video> pageFromDb = videoService.getVideos(user.getTwitterUserId(), 0,2);

        Assertions.assertEquals(page,pageFromDb);

    }





}

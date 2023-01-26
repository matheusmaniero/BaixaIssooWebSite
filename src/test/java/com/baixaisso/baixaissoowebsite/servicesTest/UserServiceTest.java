package com.baixaisso.baixaissoowebsite.servicesTest;

import com.baixaisso.baixaissoowebsite.model.User;
import com.baixaisso.baixaissoowebsite.model.Video;
import com.baixaisso.baixaissoowebsite.repositories.UserRepository;
import com.baixaisso.baixaissoowebsite.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Before
    public void setup(){
        Video video1 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1616381396838191104/pu/vid/636x360/tVhGyKRk7_xMkrk8.mp4?tag=12",1674213485000L); //Fri Jan 20 2023 11:18:05
        Video video2 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615866775710896129/pu/vid/404x720/DbhLgjv2kIusdoP8.mp4?tag=12",1674212857000L); // Fri Jan 20 2023 11:07:37
        Video video3 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615303762142457857/pu/vid/360x638/cmaWfKEyS5d058DQ.mp4?tag=12",1674133973000L); // Thu Jan 19 2023 13:12:53
        List<Video> videos =  Arrays.asList(video1,video2,video3);
        User user = new User(1403858370730278915L,"maniero87929783");
        user.setVideos(videos);

        Mockito.when(userRepository.findByScreenName(user.getScreenName())).thenReturn(user);

    }

    @TestConfiguration
    static class UserServiceTestConfiguration{
        @Bean
        public UserService userService(){
            return new UserService();
        }

    }

    @Test
    public void getUserByScreenName(){
        Video video1 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1616381396838191104/pu/vid/636x360/tVhGyKRk7_xMkrk8.mp4?tag=12",1674213485000L); //Fri Jan 20 2023 11:18:05
        Video video2 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615866775710896129/pu/vid/404x720/DbhLgjv2kIusdoP8.mp4?tag=12",1674212857000L); // Fri Jan 20 2023 11:07:37
        Video video3 = new Video(1403858370730278915L,"https://video.twimg.com/ext_tw_video/1615303762142457857/pu/vid/360x638/cmaWfKEyS5d058DQ.mp4?tag=12",1674133973000L); // Thu Jan 19 2023 13:12:53
        List<Video> videos =  Arrays.asList(video1,video2,video3);
        User user = new User(1403858370730278915L,"maniero87929783");
        user.setVideos(videos);

        String screenName = "maniero87929783";

        User userFromDb = userService.getUser(screenName);

        Assertions.assertEquals(userFromDb,user);

    }

}

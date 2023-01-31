package com.baixaisso.baixaissoowebsite.model;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "twitter_user_id")
    private Long twitterUserId;
    @Column(name = "twitter_screen_name")
    private String screenName;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "twitter_user_id")
    private List<Video> videos = new ArrayList<>();

    public User(){

    }

    public User(Long twitterUserId, String screenName) {
        this.twitterUserId = twitterUserId;
        this.screenName = screenName;
    }

    public Long getTwitterUserId() {
        return twitterUserId;
    }

    public void setTwitterUserId(Long twitterUserId) {
        this.twitterUserId = twitterUserId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public List<Video> getVideos() {
        return videos;    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(twitterUserId, user.twitterUserId) && Objects.equals(screenName, user.screenName) && Objects.equals(videos, user.videos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(twitterUserId, screenName, videos);
    }
}

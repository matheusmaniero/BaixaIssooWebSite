package com.baixaisso.baixaissoowebsite.model;

import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(name = "videos")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "twitter_user_id")
    private Long ownerId;

    @Column(name="video_link")
    private String videoUrl;

    @Column(name="created_at")
    private Long createdAt;

    public Video(){

    }

    public Video(Long ownerId, String videoUrl, Long createdAt) {
        this.ownerId = ownerId;
        this.videoUrl = videoUrl;
        this.createdAt = createdAt;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video video)) return false;
        return Objects.equals(ownerId, video.ownerId) && Objects.equals(videoUrl, video.videoUrl) && Objects.equals(createdAt, video.createdAt);
    }

    public Integer getId() {
        return id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(ownerId, videoUrl, createdAt);
    }
}

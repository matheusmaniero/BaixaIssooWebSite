package com.baixaisso.baixaissoowebsite.services;

import com.baixaisso.baixaissoowebsite.model.Video;
import com.baixaisso.baixaissoowebsite.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public Page<Video> getVideos(Long ownerId,int page, int max){

        Pageable pageable = PageRequest.of(page,max, Sort.by("createdAt").descending());

        return videoRepository.findByOwnerId(ownerId,pageable);

    }
}

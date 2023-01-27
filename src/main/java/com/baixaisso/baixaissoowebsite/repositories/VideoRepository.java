package com.baixaisso.baixaissoowebsite.repositories;

import com.baixaisso.baixaissoowebsite.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {

    Page<Video> findByOwnerId(Long ownerId, Pageable pageable);


}

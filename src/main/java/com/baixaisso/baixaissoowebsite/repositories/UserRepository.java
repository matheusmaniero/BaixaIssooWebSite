package com.baixaisso.baixaissoowebsite.repositories;

import com.baixaisso.baixaissoowebsite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByScreenName(String screenName);


}

package com.jv.forum_api.repository;

import com.jv.forumapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByUserId(Integer userId);

    User findByUsername(String username);

}

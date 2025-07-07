package com.jv.forum_api.repository;

import com.jv.forumapi.entities.PostTag;
import com.jv.forumapi.entities.PostTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, PostTagId> {
}

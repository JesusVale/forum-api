package com.jv.forum_api.repository;

import com.jv.forumapi.entities.PostReaction;
import com.jv.forumapi.entities.PostReactionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction, PostReactionId> {

}

package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.posts.PostReactionSave;
import com.jv.forumapi.entities.PostReaction;
import com.jv.forumapi.enums.Reaction;

public interface IPostReactionService {

    public PostReaction save(PostReactionSave postReactionSave);

    public Integer delete(Integer post);

}

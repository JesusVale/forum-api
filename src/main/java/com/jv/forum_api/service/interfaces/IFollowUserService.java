package com.jv.forum_api.service.interfaces;

import com.jv.forum_api.dto.follows.FollowUsersResponse;
import com.jv.forumapi.entities.FollowUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFollowUserService {

    public FollowUsers save(Integer userFollowing);

    public Integer delete(Integer userFollowing);

    public Page<FollowUsersResponse> findFollowersByUser(Integer userId, Pageable pageable);

}

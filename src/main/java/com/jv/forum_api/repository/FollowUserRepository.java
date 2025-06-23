package com.jv.forum_api.repository;

import com.jv.forum_api.dto.follows.FollowUsersResponse;
import com.jv.forumapi.entities.FollowUsers;
import com.jv.forumapi.entities.FollowUsersId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowUserRepository extends JpaRepository<FollowUsers, FollowUsersId> {


    @Query(value = """
        SELECT
            new com.jv.forum_api.dto.follows.FollowUsersResponse(
                new com.jv.forum_api.dto.users.UserSimpleResponse(
                        U.userId,
                        U.username,
                        U.email
                ),
                new com.jv.forum_api.dto.users.UserSimpleResponse(
                        FO.userId,
                        FO.username,
                        FO.email
                )
            )
            FROM FollowUsers F
            LEFT JOIN F.id.user U
            LEFT JOIN F.id.follower FO
            WHERE U.userId = :userId
        """,
        countQuery = "SELECT COUNT(F) FROM FollowUsers F LEFT JOIN F.id.user U WHERE U.userId = :userId"
    )
    Page<FollowUsersResponse> findFollowersByUser(Integer userId, Pageable pageable);

}

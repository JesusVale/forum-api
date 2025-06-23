package com.jv.forum_api.repository;

import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forumapi.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    Boolean existsByPostId(Integer postId);

    @Query("SELECT " +
            "new com.jv.forum_api.dto.posts.PostResponse(" +
            "P.postId, " +
            "P.title, " +
            "P.content, " +
            "new com.jv.forum_api.dto.users.UserSimpleResponse( U.userId, U.username, U.email), " +
            "P.createdAt" +
            ")" +
            " FROM Post P" +
            " JOIN P.createdBy U" +
            " WHERE P.postId = :postId")
    PostResponse findByPostId(Integer postId);

    @Query(value = "SELECT " +
            "new com.jv.forum_api.dto.posts.PostResponse(" +
            "P.postId," +
            "P.title," +
            "P.content," +
            "new com.jv.forum_api.dto.users.UserSimpleResponse( U.userId, U.username, U.email )," +
            "P.createdAt" +
            ")" +
            " FROM Post P" +
            " JOIN P.createdBy U" +
            " WHERE P.createdBy IN " +
        "(SELECT F.id.user from FollowUsers F WHERE F.id.follower.userId = :loggedUser) " +
            "ORDER BY P.createdAt DESC",
            countQuery = "SELECT COUNT(P) FROM Post P WHERE P.createdBy IN " +
                    "(SELECT F.id.user from FollowUsers F WHERE F.id.follower.userId = :loggedUser)"
    )
    Page<PostResponse> findByUsersFollowed(Integer loggedUser, Pageable pageable);

}

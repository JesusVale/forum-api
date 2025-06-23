package com.jv.forum_api.repository;

import com.jv.forum_api.dto.comments.PostCommentResponse;
import com.jv.forumapi.entities.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {

    Boolean existsByCommentId(Integer commentId);



    @Query(value = """
        SELECT new com.jv.forum_api.dto.comments.PostCommentResponse(
            PC.commentId,
            new com.jv.forum_api.dto.posts.PostSimple(
                RP.postId,
                RP.title,
                new com.jv.forum_api.dto.users.UserSimpleResponse(
                    RP.createdBy.userId,
                    RP.createdBy.username,
                    RP.createdBy.email
                )
            ),
            null,
            PC.content,
            new com.jv.forum_api.dto.users.UserSimpleResponse(
                PC.createdBy.userId,
                PC.createdBy.username,
                PC.createdBy.email
            ),
            PC.createdAt
        )
        from PostComment PC
        LEFT JOIN PC.rootPost RP
        WHERE PC.commentId = :commentId
    """,
            countQuery = "SELECT COUNT(PC) FROM PostComment PC WHERE PC.commentId = :commentId")
    PostCommentResponse findByCommentId(Integer commentId);

    @Query(value = """
        SELECT new com.jv.forum_api.dto.comments.PostCommentResponse(
            PC.commentId,
            new com.jv.forum_api.dto.posts.PostSimple(
                RP.postId,
                RP.title,
                new com.jv.forum_api.dto.users.UserSimpleResponse(
                    RP.createdBy.userId,
                    RP.createdBy.username,
                    RP.createdBy.email
                )
            ),
            null,
            PC.content,
            new com.jv.forum_api.dto.users.UserSimpleResponse(
                PC.createdBy.userId,
                PC.createdBy.username,
                PC.createdBy.email
            ),
            PC.createdAt
        )
        from PostComment PC
        LEFT JOIN PC.rootPost RP
        WHERE PC.rootPost.postId = :post AND PC.commentPost IS NULL
    """,
    countQuery = "SELECT COUNT(PC) FROM PostComment PC WHERE PC.rootPost.postId = :post AND PC.commentPost IS NULL")
    Page<PostCommentResponse> findByRootPost(Integer post, Pageable pageable);


    @Query(value = """
        SELECT new com.jv.forum_api.dto.comments.PostCommentResponse(
            PC.commentId,
            new com.jv.forum_api.dto.posts.PostSimple(
                RP.postId,
                RP.title,
                new com.jv.forum_api.dto.users.UserSimpleResponse(
                    RP.createdBy.userId,
                    RP.createdBy.username,
                    RP.createdBy.email
                )
            ),
            new com.jv.forum_api.dto.comments.PostCommentSimple(
                C.commentId,
                new com.jv.forum_api.dto.users.UserSimpleResponse(
                    C.createdBy.userId,
                    C.createdBy.username,
                    C.createdBy.email
                )
            ),
            PC.content,
            new com.jv.forum_api.dto.users.UserSimpleResponse(
                PC.createdBy.userId,
                PC.createdBy.username,
                PC.createdBy.email
            ),
            PC.createdAt
        )
        from PostComment PC
        LEFT JOIN PC.rootPost RP
        LEFT JOIN PC.commentPost C
        WHERE PC.commentPost.commentId = :comment
    """,
    countQuery = "SELECT COUNT(PC) FROM PostComment PC WHERE PC.commentPost.commentId = :comment")
    Page<PostCommentResponse> findByCommentPost(Integer comment, Pageable pageable);

}

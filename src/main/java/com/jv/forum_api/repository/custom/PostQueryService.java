package com.jv.forum_api.repository.custom;

import com.jv.forum_api.dto.posts.PostFilter;
import com.jv.forum_api.dto.posts.PostResponse;
import com.jv.forum_api.dto.users.UserSimpleResponse;
import com.jv.forumapi.entities.Post;
import com.jv.forumapi.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostQueryService {


    @PersistenceContext
    private EntityManager em;


    public Page<PostResponse> findByFilters(PostFilter filter, Pageable pageable) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = cb.createTupleQuery();
        Root<Post> root = query.from(Post.class);
        Join<Post, User> user = root.join("createdBy");

        List<Predicate> predicates = buildPredicates(cb, root, user, filter);

        query.multiselect(
                root.get("postId"),
                root.get("title"),
                root.get("content"),
                user.get("userId"),
                user.get("username"),
                user.get("email"),
                root.get("createdAt")
        ).where(predicates.toArray(new Predicate[0]));

        TypedQuery<Tuple> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<PostResponse> resultList = typedQuery.getResultList()
                .stream().map(t ->
                        new PostResponse(
                                t.get(0, Integer.class),
                                t.get(1, String.class),
                                t.get(2, String.class),
                                new UserSimpleResponse(
                                        t.get(3, Integer.class),
                                        t.get(4, String.class),
                                        t.get(5, String.class)
                                ),
                                t.get(6, LocalDateTime.class)
                        )).toList();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Post> countRoot = countQuery.from(Post.class);
        Join<Post, User> userCount = root.join("createdBy");
        List<Predicate> countPredicates = buildPredicates(cb, countRoot, userCount, filter);
        countQuery.select(cb.count(countRoot)).where(countPredicates.toArray(new Predicate[0]));

        Long total = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(resultList, pageable, total);


    }

    private List<Predicate> buildPredicates(CriteriaBuilder cb, Root<Post> root, Join<Post, User> user, PostFilter filter) {

        List<Predicate> predicates = new ArrayList<>();

        if(filter.s() != null && !filter.s().isBlank()) {
            predicates.add(cb.like(root.get("title"), "%" + filter.s() + "%"));
        }

        if(filter.startDate() != null && filter.endDate() != null) {
            predicates.add(cb.between(root.get("createdAt"), filter.startDate(), filter.endDate()));
        }

        if(filter.userId() != null) {
            predicates.add(cb.equal(user.get("userId"), filter.userId()));
        }

        return predicates;
    }


}

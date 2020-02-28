package com.spring1.spring2.spring3.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Entity class, pk type>
public interface PostsRepository extends JpaRepository<Posts, Long> {

}

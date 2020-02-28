package com.spring1.spring2.spring3.domain.posts;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringRunner.class)
// @SpringBootTest를 사용할 경우 h2 db를 자동으로 실행
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    // @After : 단위테스트(unit test) 끝날때마다 수행된다
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // save() : insert/update 실행(id값 있으면 update, 없으면 insert)
        postsRepository.save(Posts.builder().title(title).content(content).author("test123@naver.com").build());

        // findAll() : 모두 조회
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);

        postsRepository.save(Posts.builder().title("title").content("content").author("author").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        //log.debug("***createDate***" + posts.getCreateDate() + ",modifiedDate=" + posts.getModifiedDate());
        System.out.println(now);
        System.out.println("createDate = " + posts.getCreateDate());
        System.out.println("modifiedDate = " + posts.getModifiedDate());
        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}

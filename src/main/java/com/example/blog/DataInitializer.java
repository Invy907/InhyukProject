package com.example.blog;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(PostRepository postRepository, CommentRepository commentRepository) {
        return args -> {
            // 게시글 더미 데이터 추가
            Post post1 = new Post();
            post1.setTitle("Hello");
            post1.setContent("Nice to meet you");
            post1.setAuthor("Inhyuk");
            post1.setCreatedAt(LocalDateTime.now());
            post1.setUpdatedAt(LocalDateTime.now());
            postRepository.save(post1);

            Post post2 = new Post();
            post2.setTitle("Second Post");
            post2.setContent("This is the content of the second post.");
            post2.setAuthor("Author2");
            post2.setCreatedAt(LocalDateTime.now());
            post2.setUpdatedAt(LocalDateTime.now());
            postRepository.save(post2);

            // 댓글 더미 데이터 추가
            Comment comment1 = new Comment();
            comment1.setPost(post1);  // 첫 번째 게시글에 대한 댓글
            comment1.setNickname("Commenter1");
            comment1.setBody("hi!");
            comment1.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment1);

            Comment comment2 = new Comment();
            comment2.setPost(post1);  // 첫 번째 게시글에 대한 댓글
            comment2.setNickname("Commenter2");
            comment2.setBody("Hello");
            comment2.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment2);

            Comment comment3 = new Comment();
            comment3.setPost(post2);  // 두 번째 게시글에 대한 댓글
            comment3.setNickname("Commenter3");
            comment3.setBody("Interesting read.");
            comment3.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment3);
        };
    }
}
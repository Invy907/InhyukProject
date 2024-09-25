package com.example.blog.entity;

import com.example.blog.dto.PostDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;
    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void patch(Post post) {
        if (post.getTitle() != null) {
            this.title = post.getTitle();
        }
        if (post.getContent() != null) {
            this.content = post.getContent();
        }
        if (post.getAuthor() != null) {
            this.author = post.getAuthor();
        }
        if (post.getUpdatedAt() != null) {
            this.updatedAt = post.getUpdatedAt();
        }
    }
    public  static Post toEntity(PostDTO dto) {
        Post post = new Post();
        post.setId(dto.getId());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setAuthor(dto.getAuthor());
        post.setCreatedAt(dto.getCreatedAt());
        post.setUpdatedAt(dto.getUpdatedAt());
        return post;
    }
    public static PostDTO toDTO(Post post) {
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setAuthor(post.getAuthor());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt=LocalDateTime.now();
    }

    // 기존 엔티티에서 일부 필드만 업데이트하는 메소드
    public void patch(PostDTO postDTO) {
        if (postDTO.getTitle() != null) {
            this.title = postDTO.getTitle();
        }
        if (postDTO.getContent() != null) {
            this.content = postDTO.getContent();
        }
        // 다른 필드도 필요에 따라 추가
        this.updatedAt = LocalDateTime.now();  // 업데이트 시점 기록
    }


}



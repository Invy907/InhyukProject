package com.example.blog.dto;

import com.example.blog.entity.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import lombok.*;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}

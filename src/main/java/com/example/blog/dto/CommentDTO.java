package com.example.blog.dto;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class CommentDTO {
    private Long commentId;
    private Long postId;
    private String nickname;
    private String body;
    private LocalDateTime createdAt;

    // Convert entity to DTO
    public static CommentDTO toDTO(Comment comment) {
       return new CommentDTO(
               comment.getCommentId(),
               comment.getPost().getId(),
               comment.getNickname(),
               comment.getBody(),
               comment.getCreatedAt());
    }


}



//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@ToString
//public class CommentDto {
//    private Long id;
//    @JsonProperty("article_id") // 제이슨에서 날라올때는 이렇게 날라온다고 설정
//    private Long articleId;
//    private String nickname;
//    private String body;
//
//    public static CommentDto createCommentDto(Comment comment) {
//        return new CommentDto(
//                comment.getId(),
//                comment.getArticle().getId(),
//                comment.getNickname(),
//                comment.getBody()
//        );
//    }
//}
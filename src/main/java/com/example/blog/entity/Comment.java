package com.example.blog.entity;

import com.example.blog.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
@Slf4j
@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 하나씩 증가 시킴
    private Long commentId;
    @ManyToOne
    @JoinColumn(name = "post_id") // 대상정보의 컬럼 (FK)
    private Post post;

    @Column
    private String nickname;
    @Column
    private String body;
    @Column(name = "created_at")
    private LocalDateTime createdAt;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now(); // 생성 시 현재 시간 설정
    }

    public static Comment toEntity(CommentDTO commentDTO, Post post) {
        // 예외 처리 로직 포함
        if (commentDTO.getCommentId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 ID가 없어야 합니다.");
        if (!commentDTO.getPostId().equals(post.getId()))
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 ID가 잘못되었습니다.");

        return new Comment(
                commentDTO.getCommentId(),
                post,  // DTO에서 전달받은 post 객체
                commentDTO.getNickname(),
                commentDTO.getBody(),
                commentDTO.getCreatedAt()
        );
    }
        public void patch(CommentDTO dto) {
        // 예외 발생
        if (this.commentId != dto.getCommentId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }

}
//    public static Comment createComment(CommentDto dto, Article article) {
//        // 예외 발생
//        if (dto.getId() != null)
//            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
//        if (dto.getArticleId() != article.getId())
//            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
//
//        // 엔티티 생성 및 반환
//        return new Comment(
//                dto.getId(),
//                article,
//                dto.getNickname(),
//                dto.getBody()
//        );






//@Entity
//@Getter
//@ToString
//@AllArgsConstructor
//@NoArgsConstructor
//public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 하나씩 증가 시키게함
//    private Long id;
//
//    @ManyToOne // 여러개의 코멘트가 하나의 아티클에 달림!
//    @JoinColumn(name = "article_id") // 대상정보의 컬럼 (FK)
//    private Article article;
//    @Column
//    private String nickname;
//    @Column
//    private String body;
//
//
//    public static Comment createComment(CommentDto dto, Article article) {
//        // 예외 발생
//        if (dto.getId() != null)
//            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
//        if (dto.getArticleId() != article.getId())
//            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
//
//        // 엔티티 생성 및 반환
//        return new Comment(
//                dto.getId(),
//                article,
//                dto.getNickname(),
//                dto.getBody()
//        );
//    }
//
//    public void patch(CommentDto dto) {
//        // 예외 발생
//        if (this.id != dto.getId())
//            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
//        // 객체를 갱신
//        if (dto.getNickname() != null)
//            this.nickname = dto.getNickname();
//        if (dto.getBody() != null)
//            this.body = dto.getBody();
//    }
//}

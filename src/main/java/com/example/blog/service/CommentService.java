package com.example.blog.service;

import com.example.blog.dto.CommentDTO;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.blog.entity.Post.toDTO;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    public List<CommentDTO> getCommentsByPostId(Long id) {
        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDTO> commentDTOs = comments.stream()
                .map(CommentDTO::toDTO)
                .collect(Collectors.toList());
        return commentDTOs;
    }

    public CommentDTO getSingleCommentByPostId(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new EntityNotFoundException("commentId " + commentId + " 댓글을 찾지 못했습니다"));
        return CommentDTO.toDTO(comment);
    }

    public CommentDTO createComment(Long PostId, CommentDTO commentDTO) {
        Post post = postRepository.findById(PostId)
                .orElseThrow(() -> new EntityNotFoundException(PostId +"게시글을 찾을수 없습니다"));
        Comment comment = Comment.toEntity(commentDTO, post);
        Comment created = commentRepository.save(comment);
        return CommentDTO.toDTO(created);
    }


    public CommentDTO updateComment(Long CommentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new EntityNotFoundException("commentId " + CommentId + " 댓글을 찾지 못했습니다"));
        comment.patch(commentDTO);
        Comment updated = commentRepository.save(comment);
        return CommentDTO.toDTO(updated);
    }

    public CommentDTO deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).
                orElseThrow(()->new EntityNotFoundException("commentId " + commentId + " 댓글을 찾지 못했습니다"));
        commentRepository.delete(comment);
        return CommentDTO.toDTO(comment);
    }


}

//    @Autowired
//    private CommentRepository commentRepository;
//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    public List<CommentDto> comments(Long articleId) {
//        // 조회: 댓글 목록
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 변환: 엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        // 반환
//        return dtos;
//    }
//
//    @Transactional //DB에 변경이 일어날수있기때문에 중간에 문제가 생기면 롤백 할수있게 트랜잭션 처리 해야됨
//    public CommentDto create(Long articleId, CommentDto dto) {
//
//        // 게시글 조회 및 예외 발생
//        Article article = articleRepository.findById(articleId)
//                .orElseThrow(() -> new IllegalArgumentException()); // 없다면 예외발생해서 아래것들이 수행되지 않음
//        // 댓글 엔티티 생성
//        Comment comment = Comment.createComment(dto,article);
//        // 댓글엔티티를 디비로 저장
//        Comment created = commentRepository.save(comment);
//        // DTO로 변경하여 반환
//        return CommentDto.createCommentDto(created);
//
//    }
//
//    @Transactional
//    public CommentDto update(Long id, CommentDto dto) {
//        // 댓글 조회 및 예외 발생
//        Comment target = commentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
//        // 댓글 수정
//        target.patch(dto);
//
//        // DB로 갱신
//        Comment updated = commentRepository.save(target);
//
//        // 댓글 엔티티를 DTO로 변환 및 반환
//        return CommentDto.createCommentDto(updated);
//    }
//
//    @Transactional
//    public CommentDto delete(Long id) {
//        // 댓글 조회 및 예외 발생
//        Comment target = commentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 없습니다."));
//        // 댓글을 DB에서 삭제
//        commentRepository.delete(target);
//
//        // 삭제 댓글을 DTO로 반환
//        return CommentDto.createCommentDto(target);
//    }




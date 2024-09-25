package com.example.blog.api;

import com.example.blog.dto.CommentDTO;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentAPIController {

    @Autowired
    public CommentService commentService;


    @PostMapping("/api/post/{postId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable Long postId,@RequestBody CommentDTO commentDTO){
        CommentDTO commentDto= commentService.createComment(postId,commentDTO);
        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

//    @PostMapping("/api/articles/{articleId}/comments")
//    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {
//        // 서비스에게 위임
//        CommentDto createdDto = commentService.create(articleId, dto);
//        // 결과 응답
//        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
//    }

    @PatchMapping("/api/comments/{commentId}")
     public ResponseEntity<CommentDTO> update(@PathVariable Long commentId,@RequestBody CommentDTO dto){
         CommentDTO updatedDto  = commentService.updateComment(commentId,dto);
         return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

//    @PatchMapping("/api/comments/{id}")
//    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {
//        // 서비스에게 위임
//        CommentDto updatedDto = commentService.update(id, dto);
//        // 결과 응답
//        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
//    }
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long commentId){
       CommentDTO commentDTO = commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
    }



}

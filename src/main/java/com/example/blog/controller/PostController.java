package com.example.blog.controller;

import com.example.blog.dto.CommentDTO;
import com.example.blog.dto.PostDTO;
import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("username", "Inhyuk");
        return "post/test";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "post/index";
    }

    @GetMapping("/index/new")
    public String newIndex() {
        return "post/new";
    }

    @PostMapping("/index/create")
    public String createPost(PostDTO dto, Model model) {
        Post post = postService.create(dto);
        log.info(post.toString());
        if (post == null) {
            // 게시글 생성 실패 시 처리
            model.addAttribute("errorMessage", "Post creation failed!");
            return "index"; // 게시글 작성 페이지로 다시 이동
        }
        return "redirect:/index/" + post.getId();
    }

    @GetMapping("/index/{id}")
    public String certainPost(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        Post post = postService.getPostById(id);
        if (post == null) {
            // post가 null일 경우 예외를 던지거나 에러 페이지로 리다이렉트
            throw new RuntimeException("id번 아이디를 발견하지 못했습니다: " + id);
        }
        List<CommentDTO> commentDTOs = commentService.getCommentsByPostId(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = post.getCreatedAt().format(formatter);
        model.addAttribute("post", post);
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("comments", commentDTOs);
        log.info("포스트아이디: " + post.getId());
        return "post/show";
    }

    @GetMapping("/index/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return "redirect:/index";
        } // 게시글이 없으면 목록으로 리다이렉트
        model.addAttribute("post", post);
        return "post/edit";
    }

    @PostMapping("/post/update")
    public String update(PostDTO postDTO) {
        postService.updatePost(postDTO);
        return "redirect:/index/" + postDTO.getId();
    }


    //    @PostMapping("/post/update")
//    public String update(PostDTO postDTO) {
//        log.info(postDTO.toString());
//        Post post = Post.toEntity(postDTO);
//        log.info(post.toString());
//        postRepository.save(post);
//        return "redirect:/index/" + post.getId();
//    }
    @GetMapping("/index/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다.");
        Post target = postService.deletePost(id);
        rttr.addFlashAttribute("msg", "Deletion is complete!");
        return "redirect:/index";
    }

    @PostMapping("/index/{id}/comments")
    public ResponseEntity<String> addComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO){
        commentService.createComment(id,commentDTO);

      //  return "redirect:/index/" + id;
        return ResponseEntity.ok("댓글이 등록되었습니다.");
    }








}

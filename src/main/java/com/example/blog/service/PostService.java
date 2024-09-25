package com.example.blog.service;

import com.example.blog.dto.PostDTO;
import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Getter
@Setter
public class PostService {


    @Autowired
    private  PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(Post::toDTO).collect(Collectors.toList());
    }

    public Post getPostById(Long id) {

        return postRepository.findById(id).orElse(null);
    }


    public Post create(PostDTO dto) {
        log.info(dto.toString());
        Post post = Post.toEntity(dto);
        if (post.getId() != null) {
            return null;
        }
        log.info(post.toString());
        return postRepository.save(post);
    }

        public Post updatePost(PostDTO postdto){
            log.info(postdto.toString());
            Post post = Post.toEntity(postdto);
            Post target = postRepository.findById(postdto.getId()).orElseThrow(() -> new EntityNotFoundException("Post not found with id " + post.getId()));
            target.patch(post);
            return postRepository.save(target);
        }


        public Post deletePost (Long id){
            Post target = postRepository.findById(id).
                    orElseThrow(() -> new IllegalArgumentException("게시글 삭제 대상이 없습니다"));
            postRepository.delete(target);
            return target;
        }

}




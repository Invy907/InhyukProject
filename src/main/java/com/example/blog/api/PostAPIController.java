package com.example.blog.api;

import com.example.blog.dto.PostDTO;
import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostAPIController {
    @Autowired
    public PostService postService;

    @Autowired
    private PostRepository postRepository;  // PostRepository를 주입받음
@GetMapping("/api/posts")
    public List<PostDTO> index(){
    return postService.getAllPosts();
}
@GetMapping("/api/posts/{id}")
public PostDTO showPost(@PathVariable Long id){
 Post post = postService.getPostById(id);
 PostDTO postDTO = Post.toDTO(post);
 return postDTO;
}
@PostMapping("/api/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO){
    Post created = postService.create(postDTO);
    return (created != null) ?
            ResponseEntity.status(HttpStatus.OK).body(Post.toDTO(created)) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}
@PatchMapping("/api/posts/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable Long id){
    Post updated = postService.updatePost(postDTO);
    return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(Post.toDTO(updated)) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
}

@DeleteMapping("/api/posts/{id}")
    public ResponseEntity<PostDTO> deletePost(@PathVariable Long id){
    Post delete = postService.deletePost(id);
    return (delete != null) ? ResponseEntity.status(HttpStatus.OK).body(Post.toDTO(delete)) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
}


}
//// GET
//@GetMapping("/api/articles")
//public List<Article> index() {
//    return articleService.index();
//}
//
//@GetMapping("/api/articles/{id}")
//public Article show(@PathVariable Long id) {
//    return articleService.show(id);
//}
//
////Post
//@PostMapping("/api/articles")
//public ResponseEntity<Article> create(@RequestBody /*제이슨데이터 받기*/ArticleForm dto) {
//    Article created = articleService.create(dto);
//    return (created != null) ?
//            ResponseEntity.status(HttpStatus.OK).body(created) :
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//}
//
////Patch
//@PatchMapping("/api/articles/{id}")
//public ResponseEntity<Article> update(@RequestBody ArticleForm dto,
//                                      @PathVariable Long id) {
//    Article updated = articleService.update(id, dto);
//    return (updated != null) ?
//            ResponseEntity.status(HttpStatus.OK).body(updated) :
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//}
//
////Delete
//@DeleteMapping("/api/articles/{id}")
//public ResponseEntity<Article> delete(@PathVariable Long id) {
//    Article deleted = articleService.delete(id);
//    return (deleted != null) ?
//            ResponseEntity.status(HttpStatus.OK).build() :
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//}
//
//// 트랜잭션 (실패하면 롤백)
//
//@PostMapping("/api/transaction-test")
//public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
//    List<Article>createdList = articleService.createArticles(dtos);
//    return (createdList != null) ?
//            ResponseEntity.status(HttpStatus.OK).body(createdList) :
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//}
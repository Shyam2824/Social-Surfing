package com.shyam.demo.contorolers;

import com.shyam.demo.models.Post;
import com.shyam.demo.models.User;
import com.shyam.demo.response.APIResponse;
import com.shyam.demo.service.PostService;
import com.shyam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   // To  define the end point of the API
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @PostMapping("/api/posts")
    public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception {

       User reqUser= userService.findUserByJwt(jwt);
        Post createdPost= postService.createNewPost(post, reqUser.getId());
        return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/posts/{postId}")
    public ResponseEntity<APIResponse>deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser= userService.findUserByJwt(jwt);
        String message= postService.deletePost(postId, reqUser.getId());
        APIResponse res= new APIResponse(message, true);
        return new ResponseEntity<APIResponse>(res,HttpStatus.OK);
    }

    @GetMapping("/api/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postId) throws Exception {
        Post post= postService.findPostById(postId);
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/posts/user/{userid}")
    public ResponseEntity<List<Post>>findUsersPost(@PathVariable Integer userId){
        List<Post> posts= postService.findPostByUserId(userId);
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @GetMapping("/api/posts")
    public ResponseEntity<List<Post>>findAllPost(){
        List<Post> posts= postService.findAllPost();
        return new ResponseEntity<List<Post>>(posts,HttpStatus.OK);
    }

    @PutMapping("/api/posts/save/{postId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception {

        User reqUser= userService.findUserByJwt(jwt);
        Post post= postService.savedPost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/posts/like/{postId}/")
    public ResponseEntity<Post> likePostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt ) throws Exception {

        User reqUser= userService.findUserByJwt(jwt);
        Post post= postService.likePost(postId, reqUser.getId());
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}

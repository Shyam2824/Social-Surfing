package com.shyam.demo.contorolers;

import com.shyam.demo.models.Reels;
import com.shyam.demo.models.User;
import com.shyam.demo.service.ReelsService;
import com.shyam.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReelsController {

    @Autowired
    private ReelsService reelsService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/reels")
    public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt){

        User reqUser= userService.findUserByJwt(jwt);
        Reels createdReels= reelsService.createReel(reel, reqUser);

        return createdReels;

    }

    @GetMapping("/api/reels")
    public List <Reels> findAllReels(){

        List <Reels> reels= reelsService.findAllReels();

        return reels;

    }
    @GetMapping("/api/reels/user/{userId}")
    public List <Reels> findUserReels(@PathVariable Integer userId) throws Exception{

        List <Reels> reels= reelsService.findAllReels();

        return reels;

    }
}

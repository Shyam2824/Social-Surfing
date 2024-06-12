package com.shyam.demo.contorolers;

import org.springframework.web.bind.annotation.*;

@RestController  // it is used to define end point
public class HomeController {

@GetMapping // used to take data from database
    public String homeControllerHandeler(){
        return "that should be shyam";
    }
    //@PutMapping// any change in database
    //@PostMapping// to add new data
    //@DeleteMapping// to delete the data from database
}

package com.codez.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {


    @GetMapping("/test")
    String test(){
        return "200";
    }

}

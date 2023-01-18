package com.codeup.springinitializer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.ui.Model;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String allPosts(Model model){
        ArrayList<Post> all = new ArrayList<>(2);
        Post post1 = new Post("post #1 title", "post #1 body");
        Post post2 = new Post("post #2 title", "post #2 body");
        all.add(post1);
        all.add(post2);
        model.addAttribute("title", "All Posts");
        model.addAttribute("allPosts", all);
        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String post(Model model){
        Post post1 = new Post("post #1 title", "post #1 body");
        model.addAttribute("title", "Individual Post");
        model.addAttribute("postTitle", post1.getTitle());
        model.addAttribute("postBody", post1.getBody());
        return "posts/show";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String getCreate(){
        return "This will pull the create a post page.";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String postCreate(){
        return "This will post the create a post page.";
    }
}

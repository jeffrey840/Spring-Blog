package com.codeup.springinitializer;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import org.springframework.ui.Model;
import org.springframework.stereotype.Service;

@Controller
public class PostController {

    private final UserRepository userDao;
    private final PostRepository postDao;

    private final EmailService emailService;

    public PostController(PostRepository postDao,UserRepository userDao, EmailService emailService){
        this.userDao = userDao;
        this.postDao = postDao;
        this.emailService = emailService;

    }


    @GetMapping("/posts")
    public String postIndex(Model model){
        model.addAttribute("posts", postDao.findAll());
        model.addAttribute("title", "Post Index");
        return "posts/index";
    }

    @GetMapping(path = "/posts/{id}")
    public String viewPost(@PathVariable long id, Model model){
        model.addAttribute("title", "Individual Post");
        model.addAttribute("post", postDao.findById(id));
        Post post = postDao.getReferenceById(id);
        User user = userDao.getReferenceById(post.getUser().getId());
        model.addAttribute("postTitle", post.getTitle());
        model.addAttribute("postBody", post.getBody());
        model.addAttribute("postID", post.getId());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("user", user);
        return "posts/show";
    }

    @GetMapping(path = "/posts/{id}/edit")
    public String getEdit(@PathVariable long id, Model model){
        model.addAttribute("title", "Edit Post");
        Post post = postDao.getReferenceById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping(path = "/posts/{id}/edit")
    public String postEdit(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post post = postDao.getReferenceById(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts";
    }


    @GetMapping(path = "/posts/create")
    public String getCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String postCreate(@ModelAttribute Post post){
        post.setUser(userDao.getReferenceById((1L)));
        emailService.prepareAndSend(post, "Your latest blog post: " + post.getTitle(), "This is the body of your post!" + post.getBody());
        postDao.save(post);
        return "redirect:/posts";
    }
}

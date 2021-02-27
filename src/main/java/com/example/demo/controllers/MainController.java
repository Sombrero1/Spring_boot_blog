package com.example.demo.controllers;

import com.example.demo.models.Post;
import com.example.demo.repo.PostReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping(path="/",method={RequestMethod.PATCH})
public class MainController {

    @Autowired
    PostReprository postReprository;

    @GetMapping("/")
    public String greeting( Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
    @GetMapping("/blog")
    public String blog( Model model) {
        Iterable<Post> posts = postReprository.findAll();
        model.addAttribute("title", "Блог");
        model.addAttribute("posts", posts);
        return "blog-main";
    }



    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "Про нас");
        return "about";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@ModelAttribute("post") Post post){
        postReprository.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model){
        model.addAttribute("post", new Post());
        return "blog-add";
    }

    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable("id") long id, Model model) {
        Optional<Post> optional = postReprository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        optional.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-post";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable("id") long id, Model model) {
        model.addAttribute("post", (Post)postReprository.findById(id).orElseThrow());
        return "blog-edit";
    }


    @PatchMapping("/blog/{id}")
    public String blogEditPatch(@ModelAttribute("post") Post post, Model model) {
        System.out.println("Hello");
//        postReprository.save(post);
        return "redirect:/blog";
    }




}
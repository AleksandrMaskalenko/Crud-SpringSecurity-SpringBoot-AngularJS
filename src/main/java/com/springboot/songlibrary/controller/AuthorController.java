package com.springboot.songlibrary.controller;

import com.springboot.songlibrary.model.Author;
import com.springboot.songlibrary.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("/authors")
    public List<Author> getAuthor() {
        return authorService.getAllAuthor();
    }

    @RequestMapping(value = "/author/add", method = RequestMethod.POST)
    public void addAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }
}

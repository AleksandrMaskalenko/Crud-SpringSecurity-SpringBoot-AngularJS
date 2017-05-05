package com.springboot.songlibrary.service;

import com.springboot.songlibrary.DAO.AuthorDao;
import com.springboot.songlibrary.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorDao authorDao;

    public List<Author> getAllAuthor() {
        return authorDao.findAll();
    }

    public void saveAuthor(Author author) {
        authorDao.save(author);
    }

}

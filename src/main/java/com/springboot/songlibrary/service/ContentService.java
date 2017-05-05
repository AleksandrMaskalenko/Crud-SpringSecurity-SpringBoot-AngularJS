package com.springboot.songlibrary.service;

import com.springboot.songlibrary.DAO.ContentDao;
import com.springboot.songlibrary.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    @Autowired
    private ContentDao contentDao;

    public void saveContent(Content content) {
        contentDao.save(content);
    }


}

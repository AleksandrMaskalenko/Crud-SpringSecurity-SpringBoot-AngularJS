package com.springboot.songlibrary.DAO;


import com.springboot.songlibrary.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentDao extends JpaRepository<Content, Integer>{
}

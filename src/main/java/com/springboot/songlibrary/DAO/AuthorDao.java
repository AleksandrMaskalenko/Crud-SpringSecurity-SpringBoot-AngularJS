package com.springboot.songlibrary.DAO;


import com.springboot.songlibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends JpaRepository<Author, Integer> {

}

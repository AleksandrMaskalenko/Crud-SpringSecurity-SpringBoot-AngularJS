package com.springboot.songlibrary.DAO;

import com.springboot.songlibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
